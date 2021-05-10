package com.clillo.dmx.comm.dmx;

import ch.bildspur.artnet.ArtNetClient;
import ch.bildspur.artnet.events.ArtNetServerEventAdapter;
import ch.bildspur.artnet.packets.ArtDmxPacket;
import ch.bildspur.artnet.packets.ArtNetPacket;

public class Artnet {
	
	public static void main(String[] args) {
        ArtNetClient artnet = new ArtNetClient();


       artnet.getArtNetServer().addListener(
                new ArtNetServerEventAdapter() {
                    @Override public void artNetPacketReceived(ArtNetPacket packet) {
                    	if (packet instanceof ArtDmxPacket){
	                    	ArtDmxPacket dmx = (ArtDmxPacket)packet;
	                   	    System.out.println("new packet received!\t"+(dmx.getDmxData()[0] & 0xFF)+"\t"+(dmx.getDmxData()[1] & 0xFF));
	                    	
	                    	// byte[] data = packet.getData();
	                    	 ///System.out.println("new packet received!\t"+(data[0] & 0xFF)+"\t"+(data[1] & 0xFF) +"\t"+ (data[2] & 0xFF));
	                    }
                    
                    }
                });

        artnet.start();
  

	}

}
