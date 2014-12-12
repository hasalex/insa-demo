package fr.sewatech.formation.appserv.service;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeavyData {

    private Logger log = Logger.getLogger(HeavyData.class);
    
    private String str;
	private List<Long> lg;

	public HeavyData(int size) {
		lg = new ArrayList<Long>();
        StringBuilder strBuf = new StringBuilder(size);
		Random rd = new Random();
		for (int i = 0; i < size; i++) {
            strBuf.append(rd.nextInt(0x186a0));
			lg.add(new Long(i));
		}
        str = strBuf.toString();
	}

    public HeavyData interned() {
        str = str.intern();
        return this;
    }
}
