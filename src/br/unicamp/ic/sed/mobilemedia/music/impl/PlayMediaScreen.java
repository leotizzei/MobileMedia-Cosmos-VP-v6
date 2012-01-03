package br.unicamp.ic.sed.mobilemedia.music.impl;

import java.io.InputStream;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.PitchControl;
import javax.microedition.media.control.TempoControl;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;


public class PlayMediaScreen extends Canvas implements ItemStateListener{
	
	public static final Command start = new Command("Start", Command.BACK, 0);
	public static final Command back = new Command("Back", Command.ITEM, 1);
	public static final Command stop = new Command("Stop", Command.ITEM, 1);	
	
	  // the midi player
	  Player midiPlayer = null;
	  
	  // the controls
	  VolumeControl volControl = null;
	  PitchControl pitchControl = null;
	  TempoControl tempoControl = null;
	  
	  // the visual elements
	  Form form = null;
	  Gauge volGauge = null;
	  Gauge pitchGauge = null;
	  Gauge tempoGauge = null;
	  
	  private MIDlet midlet;
	
	public PlayMediaScreen(MIDlet midlet, InputStream storedMusic, String type) {
	
		try {
			this.midlet = midlet;
			
			midiPlayer = Manager.createPlayer(storedMusic, type);
			midiPlayer.prefetch();
			volControl = (VolumeControl) midiPlayer.getControl(
		    	"javax.microedition.media.control.VolumeControl");
			pitchControl = (PitchControl) midiPlayer.getControl(
		    	"javax.microedition.media.control.PitchControl");
			tempoControl = (TempoControl) midiPlayer.getControl(
		    	"javax.microedition.media.control.TempoControl");
			form = new Form("MIDI Player", null);
	  
			this.setButtons(form);
	  
			volGauge = new Gauge("Volume: 50", true, 100, 50);
			tempoGauge = new Gauge("Tempo: 120", true, 30, 12);
			pitchGauge = new Gauge("Pitch: 0", true, 10, 5);
			     
			form.append(volGauge);
			form.append(tempoGauge);
			form.append(pitchGauge);
			      
			form.setItemStateListener(this);   
		    lastScreen = Display.getDisplay(midlet).getCurrent();
		    Display.getDisplay(midlet).setCurrent(form);
		      
		} catch(Exception e) {
			System.err.println(e);
		}
	}
  
	private void setButtons( Form form ){
		form.addCommand(back);
		form.addCommand(start);
		form.addCommand(stop);
	}
	
	  public void setFormController( AbstractController controller ){
		  form.setCommandListener(controller);
	  }
	
	  public void itemStateChanged(final Item item) {
		    
		    if(!(item instanceof Gauge)) return;

		    Gauge gauge = (Gauge)item;
		    int val = gauge.getValue();
		   

		    if(item == volGauge) { 
		      volControl.setLevel(val); 
		      volGauge.setLabel("Volume: " + val);
		    }
		    if(item == tempoGauge && tempoControl != null) {
		      tempoControl.setTempo((val) * 10 * 1000);
		      tempoGauge.setLabel("Tempo: " + (val * 10));
		    }
		    

		    if(item == pitchGauge && pitchControl != null) {
		      pitchControl.setPitch((val - 5) * 12 * 1000);
		      pitchGauge.setLabel("Pitch: " + (val - 5));
		    }
		  }
	

	  public void startPlay() {
		    try {
		      // start the MIDI player if it was created
		      if(midiPlayer != null) {
		        midiPlayer.start();
		      }      
		    } catch(Exception e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public void pausePlay() {
		    try {
		      if(midiPlayer != null) {
		        midiPlayer.stop();
		      }
		    } catch(Exception e) {
		      System.err.println(e);
		    }
		  }

	protected void paint(Graphics arg0) {
		
	}
	
	private Displayable lastScreen = null;
	
	public void dispose(){
		Display.getDisplay(midlet).setCurrent( lastScreen );
	}

}