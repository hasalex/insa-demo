package fr.sewatech.formation.appserv.service;

import fr.sewatech.formation.appserv.util.ClassUtil;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageServiceDbImpl implements MessageService {

	private Logger log = Logger.getLogger(MessageServiceDbImpl.class);

	private DataSource ds;
	private boolean close = true;

	private String dataSourceName;

	private Context namingContext;

	public MessageServiceDbImpl(String datasourceName) {
		log.debug("Instanciation " + this.hashCode() + " pour la datasource " + datasourceName);
		this.dataSourceName = datasourceName;
		try {
			namingContext = new InitialContext();
		} catch (Exception e) {
			throw new SewaException(e);
		}
	}

	public MessageServiceDbImpl(String datasourceName, boolean close) {
		this(datasourceName);
		log.debug("Close = " + close);
		this.close = close;
	}

	public Message getMessage(int messageId) {
		log.debug("Demande du message n° " + messageId);
		Connection cx = null;
		try {
			cx = getConnection();
			PreparedStatement st = cx.prepareStatement("select message from Message where id=?");
			st.setInt(1, messageId);
			ResultSet rs = st.executeQuery();
			Message message = null;
			if (rs.next()) {
				message = new Message();
				message.setText(rs.getString(1));
				message.setOrigin(ClassUtil.getLibrary(this.getClass()));
				log.debug("Réponse : " + message);
			} else {
				log.debug("Pas de réponse");
			}
			return message;
		} catch (Exception e) {
			log.debug("Problème...", e);
			throw new SewaException(e);
		} finally {
			close(cx);
		}
	}

	public int addMessage(String text) {
		log.debug("Ajout du message " + text);
		Connection cx = null;
		try {
			cx = getConnection();

			int messageId = 0;
			PreparedStatement stId = cx
					.prepareStatement("select max(id) from Message");
			ResultSet rsId = stId.executeQuery();
			if (rsId.next()) {
				messageId = rsId.getInt(1);
			}

			PreparedStatement st = cx
					.prepareStatement("insert into Message (id, message) values (?, ?)");
			st.setInt(1, messageId);
			st.setString(2, text);
			st.executeUpdate();

			return messageId;

		} catch (Exception e) {
            log.debug("Problème...", e);
			throw new SewaException(e);
		} finally {
			close(cx);
		}
	}

	public int countMessages() {
		log.debug("Demande du nombre de messages");
		Connection cx = null;
		try {
			cx = getConnection();
			PreparedStatement st = cx.prepareStatement("select count(*) from Message");
			ResultSet rs = st.executeQuery();
			rs.next();

			int count = rs.getInt(1);
			log.debug("Réponse : " + count);
			return count;
		} catch (Exception e) {
            log.debug("Problème...", e);
			throw new SewaException(e);
		} finally {
            close(cx);
		}
	}

	public void init() {
		log.debug("Initialisation des messages");
		try (Connection cx = getConnection()) {

			PreparedStatement exists = cx.prepareStatement("show tables like ?");
			exists.setString(1, "Message");
			ResultSet resultSet = exists.executeQuery();
			if (resultSet.next()) {
				log.info("La table Message existe déjà.");
				return;
			}

			log.info("Creating Message table");
			PreparedStatement create = cx.prepareStatement("CREATE TABLE Message (" +
					"  ID int(11) NOT NULL," +
					"  MESSAGE varchar(30) NOT NULL," +
					"  PRIMARY KEY  (ID)" +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1");
			create.executeUpdate();

			PreparedStatement st = cx.prepareStatement("insert into Message (id, message) values (?, ?)");

			for (int i = 0; i < 5; i++) {
				st.setLong(1, i);
				st.setString(2, "Message " + i);
				st.executeUpdate();
			}
		} catch (NameNotFoundException e) {
			log.info("Pas de connexion à la base de données");
		} catch (Exception e) {
			log.debug("Problème...", e);
			throw new SewaException(e);
		}
	}


	private Connection getConnection() throws NamingException, SQLException {
		Connection cx;
		ds = (DataSource) namingContext.lookup(dataSourceName);
		cx = ds.getConnection();
		return cx;
	}

	private void close(Connection cx) {
		try {
			if (cx != null && close) {
				log.debug("Demande de fermeture de la connexion " + cx.hashCode());
				cx.close();
			}
		} catch (SQLException e) {
			log.warn("Problème à la fermeture de la connexion", e);
		}
	}
}
