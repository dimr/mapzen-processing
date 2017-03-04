


import main.Mapzen;
import main.net.MapzenUrl;
import main.net.MapzenUrlBuilder;
import peasy.PeasyCam;
import java.util.Arrays;
import toxi.geom.Vec3D;
import toxi.processing.ToxiclibsSupport;

import java.util.List;

/**
 * Created by dimitris on 4/7/16.
 */

PeasyCam cam;
private Mapzen mapzen;
ToxiclibsSupport toxi;

//This is coordinates from your tweets, Vec3D is similar to PVector
Vec3D TweetCoordinate = new Vec3D(-122.41422f, 37.77696f, 0);
//this will hold coordinates converted to application dimensions
Vec3D convertedTweetCoordiante;
PGraphics pg;

  public void setup() {
 // **do not** use setLayrers("roads")...only buildings
  MapzenUrl url = new MapzenUrlBuilder().setLongitude(-122.409531f).setLatitude(37.782281f).setZoom(13).setLayer("buildings").setKey("vector-tiles-PADQnWp").buildUrl();

  mapzen =  new Mapzen(this, url); //this PApplet size


  cam = new PeasyCam(this, width/2, height/2, 0, 1100);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(2500);

  toxi=new ToxiclibsSupport(this);

  smooth();
  
  //coordinate convertion takes place
  convertedTweetCoordiante=mapzen.convert(TweetCoordinate);
  cam.rotateX(-45);

}



public void draw() {
  background(0);

  //cam.rotateY((frameCount*.0004)%360);
// plot of the location of the tweet
  pushMatrix();
  pushStyle();
  strokeWeight(10);
  stroke(0,0, 200);
 
  translate(convertedTweetCoordiante.x(), convertedTweetCoordiante.y(), 80);
   
  float t=map(sin(frameCount*.2),-1,1,30,40);
  //line(0, 0, 0, 0, 0, t);
  textSize(43);
  
  pushMatrix();
  rotateY(radians(-90));
  float camRot=degrees(cam.getRotations()[2]);
  if(degrees(camRot)<0)
  fill(255,0,0);
  else
  fill(0,0,255);
  float rot = degrees(camRot)<0?camRot:-camRot;
  rotateX(rot);
  text(Arrays.toString(cam.getRotations()),0,0,10);
  popMatrix();
  popStyle();
  popMatrix();
  
println(Arrays.toString(cam.getRotations())+" "+degrees(cam.getRotations()[2]));;
// for now: renderPolygons(red,green,blue,opacity) -->fill of the buildings, all sides of the building get the same fill color you set here
  mapzen.renderPolygons(190, 190, 188, 220);
  
  // drawGeoRoads(red,green,blue,strokeWeight)
  mapzen.drawGeoRoads(180, 0, 0, 1);
}

public void settings() {
  //        size(1300,850);
  size(1900, 1050, "processing.opengl.PGraphics3D");
  //        fullScreen(P3D,2);
}