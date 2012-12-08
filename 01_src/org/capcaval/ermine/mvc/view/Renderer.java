package org.capcaval.ermine.mvc.view;

import org.capcaval.ermine.mvc.view.painter.RenderInfo;

public interface Renderer<G> {

	public void render(RenderInfo info, G g);
	
	public boolean isDirty();
	public void setDirty(boolean isDirty);
}
