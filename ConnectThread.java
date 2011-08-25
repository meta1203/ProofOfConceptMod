package net.minecraft.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectThread extends Thread
{
  Socket socket = null;
  PrintWriter out = null;
  BufferedReader in = null;

  public ConnectThread(String hostName) {
    try {
      this.socket = new Socket(hostName, 8124);
      this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host: " + hostName);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the conneStepSoundion to: " + hostName);
    }
  }

  public void run() {
    try {
      while (this.in.readLine() != null) {
    	  if (this.in.readLine().equals("item")) {
    		  int id = Integer.parseInt(this.in.readLine());
    		  int textureX = Integer.parseInt(this.in.readLine());
    		  int textureY = Integer.parseInt(this.in.readLine());
    		  Item.defineItemSMP(id, textureX, textureY);
    	  }
    	  else {
        int id = Integer.parseInt(this.in.readLine());
        float hardness = Float.parseFloat(this.in.readLine());
        float resistance = Float.parseFloat(this.in.readLine());
        int stepSoundCase = Integer.parseInt(this.in.readLine());
        int materialCase = Integer.parseInt(this.in.readLine());
        String name = this.in.readLine();
        
        int[] textureID = new int[6];
        for (int x = 0; x < 6; x++) {
        	textureID[x] = Integer.parseInt(this.in.readLine());
        }
        
        StepSound ss = Block.soundStoneFootstep;
        switch (stepSoundCase) { case 0:
          ss = Block.soundClothFootstep; break;
        case 1:
          ss = Block.soundGlassFootstep; break;
        case 2:
          ss = Block.soundGrassFootstep; break;
        case 3:
          ss = Block.soundGravelFootstep; break;
        case 4:
          ss = Block.soundMetalFootstep; break;
        case 5:
          ss = Block.soundPowderFootstep; break;
        case 6:
          ss = Block.soundSandFootstep; break;
        case 7:
          ss = Block.soundStoneFootstep; break;
        case 8:
          ss = Block.soundWoodFootstep; break;
        default:
          ss = Block.soundStoneFootstep;
        }
        
        Material mat = Material.rock;
        switch (materialCase) { 
        case 0: mat = Material.builtSnow; break;
        case 1: mat = Material.cactus; break;
        case 2: mat = Material.cakeMaterial; break;
        case 3: mat = Material.circuits; break;
        case 4: mat = Material.clay; break;
        case 5: mat = Material.cloth; break;
        case 6: mat = Material.fire; break;
        case 7: mat = Material.glass; break;
        case 8: mat = Material.grassMaterial; break;
        case 9: mat = Material.ground; break;
        case 10: mat = Material.ice; break;
        case 11: mat = Material.lava; break;
        case 12: mat = Material.leaves; break;
        case 13: mat = Material.plants; break;
        case 14: mat = Material.portal; break;
        case 15: mat = Material.pumpkin; break;
        case 16: mat = Material.rock; break;
        case 17: mat = Material.sand; break;
        case 18: mat = Material.snow; break;
        case 19: mat = Material.sponge; break;
        case 20: mat = Material.tnt; break;
        case 21: mat = Material.water; break;
        case 22: mat = Material.wood; break;
        }

        Block.defineBlockSMP(id, hardness, resistance, ss, textureID, mat, name);
      }
      }
      in.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}