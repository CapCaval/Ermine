package org.capcaval.ermine.mvc.view.views._impl.jogl;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import org.capcaval.ermine.mvc.view.views.View;

import com.sun.opengl.util.GLUT;


public class CanvasOfView {
	
	List<View> listOfView = new ArrayList<View>(); 
	
	protected GLU glu;
	protected GLUT glut;

	private GLCanvas canvas;

	public CanvasOfView(){
		this.init();
	}

	public GLCanvas getDisplayComponent(){
		return this.canvas;
	}
	
	protected void init() {
		// init the static tools
		this.glu = new GLU();
		this.glut = new GLUT();

		GLCapabilities caps = new GLCapabilities();
		caps.setDoubleBuffered(true);

		this.canvas = new GLCanvas(caps);
		this.canvas.addGLEventListener(this.createGLEventListener());
		
		this.canvas.addComponentListener(new ComponentAdapter(){
    		@Override
    		public void componentResized(ComponentEvent e){
    			// re-apply the current bound it will be corrected if 
//    			for(View<GLAutoDrawable> view : listOfView){
//    				// resize all the view
//    				//view.composeAllLayer(drawable);
//    			}
    		}
    	});
		
	}
	
	protected GLEventListener createGLEventListener() {

		GLEventListener glel = new GLEventListener() {

			@Override
			public void init(GLAutoDrawable drawable) {
				GL gl = drawable.getGL();

				gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
				gl.glShadeModel(GL.GL_FLAT);
				gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
			}

			@Override
			public void display(GLAutoDrawable drawable) {

				paintAllView(drawable);

				
				int errorCode = drawable.getGL().glGetError();
				if (errorCode > 0) {
					String errorStr = glu.gluErrorString(errorCode);
					System.out.println("##"+errorStr);
					System.out.println(errorCode);
				}
				
			}


			@Override
			public void displayChanged(GLAutoDrawable arg0, boolean arg1,
					boolean arg2) {
			}

			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int w,
					int h) {
			}
		};

		return glel;
	}


	
	
	public void addView(View view){
		this.listOfView.add(view);
	}

	public void paintAllView(GLAutoDrawable drawable) {
		//
		for(View view : this.listOfView){
			//view.composeAllLayer(drawable);
		}
		
	}

	
}
