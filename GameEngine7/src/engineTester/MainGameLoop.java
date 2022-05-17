package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel modelc = OBJLoader.loadObjModel("stall", loader); 
		TexturedModel staticModelc = new TexturedModel(modelc,new ModelTexture(loader.loadTexture("texture")));
		ModelTexture texturec = staticModelc.getTexture();
		Entity entity =  new Entity(staticModelc, new Vector3f(155,0,150),0,0,0,15);
		
		
		RawModel model = OBJLoader.loadObjModel("person", loader); 
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("playerTexture")));
		ModelTexture texture = staticModel.getTexture();
		Player player =  new Player(staticModel, new Vector3f(80,-30,70),0,0,0,3); //20 represents the size of entity 80,0,70),0,0,0,5
		
		Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("finalmon")));
		//Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("mon")));
		
		Camera camera = new Camera(player);	
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			
			//entity.increaseRotation(0, 1, 0);
			camera.move();
			player.move();
			renderer.processEntity(entity);
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}