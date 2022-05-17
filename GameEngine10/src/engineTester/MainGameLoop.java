package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.Maths;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel modelc = OBJLoader.loadObjModel("stall", loader); 
		TexturedModel staticModelc = new TexturedModel(modelc,new ModelTexture(loader.loadTexture("texture")));
		//ModelTexture texturec = staticModelc.getTexture();
		Entity entity =  new Entity(staticModelc, new Vector3f(155,0,150),0,0,0,15);
		
		
		RawModel model = OBJLoader.loadObjModel("person", loader); 
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("playerTexture")));
		//ModelTexture texture = staticModel.getTexture();
		Player player =  new Player(staticModel, new Vector3f(80,-30,70),0,0,0,2); //2 represents the size of entity 80,0,70),0,0,0,5
		
		Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));
		
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture guii = new GuiTexture(loader.loadTexture("intructions"), new Vector2f(0.7f,-0.9f), new Vector2f(0.3f, 0.5f));
		GuiTexture gui = new GuiTexture(loader.loadTexture("name"), new Vector2f(0.95f,0.55f), new Vector2f(0.3f, 0.5f));
		guis.add(gui);
		guis.add(guii);
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("finalmon")));
		//Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("mon")));

		boolean b = false; //
		
		Camera camera = new Camera(player);	
		MasterRenderer renderer = new MasterRenderer(loader);
		
		while(!Display.isCloseRequested()){
			
			//entity.increaseRotation(0, 1, 0);
			camera.move();
			player.move();
			renderer.processEntity(entity);
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			renderer.render(light, camera);
			guiRenderer.render(guis);
			if (Keyboard.isKeyDown(Keyboard.KEY_I)) { 
					List<GuiTexture> info = new ArrayList<GuiTexture>();
					GuiTexture infos = new GuiTexture(loader.loadTexture("info"), new Vector2f(0f,0f), new Vector2f(0.7f, 0.9f));
					info.add(infos);
					//GuiRenderer guiRendererChance = new GuiRenderer(loader);
					guiRenderer.render(info);
					}
		
			int i= Maths.getRandomNumberInRange(1, 11);
			while (Keyboard.isKeyDown(Keyboard.KEY_C)) { // 
				List<GuiTexture> guisChance = new ArrayList<GuiTexture>();
				GuiTexture chance = new GuiTexture(loader.loadTexture(Integer.toString(i)), new Vector2f(0.0f,0.3f), new Vector2f(0.3f, 0.5f));
				guisChance.add(chance);
				GuiRenderer guiRendererChance = new GuiRenderer(loader);
				guiRendererChance.render(guisChance);	
				DisplayManager.updateDisplay();
			}guis.clear();

	
			int j=Maths.getRandomNumberInRange(12, 21);
			while (Keyboard.isKeyDown(Keyboard.KEY_T)) { // 
				List<GuiTexture> guisChance = new ArrayList<GuiTexture>();
				GuiTexture chance = new GuiTexture(loader.loadTexture(Integer.toString(j)), new Vector2f(0.0f,0.3f), new Vector2f(0.3f, 0.5f));
				guisChance.add(chance);
				GuiRenderer guiRendererChance = new GuiRenderer(loader);
				guiRendererChance.render(guisChance);	
				DisplayManager.updateDisplay();
			}guis.clear();
			
			int r=Maths.getRandomNumberInRange(22, 27);
			while (Keyboard.isKeyDown(Keyboard.KEY_R)) { // 
				List<GuiTexture> guisChance = new ArrayList<GuiTexture>();
				GuiTexture chance = new GuiTexture(loader.loadTexture(Integer.toString(r)), new Vector2f(0.0f,0.3f), new Vector2f(0.3f, 0.5f));
				guisChance.add(chance);
				GuiRenderer guiRendererChance = new GuiRenderer(loader);
				guiRendererChance.render(guisChance);	
				DisplayManager.updateDisplay();
			}guis.clear();
			
			

				
			DisplayManager.updateDisplay();
		}

		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}