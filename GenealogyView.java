package pp;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



public class GenealogyView {
	public static void main(String[] args) {
		new GenealogyView().run();
		}
	private void run() {
		Shell shell = new Shell(new Display());
		shell.setSize(365, 280);
		shell.setText("Genealogy");
		shell.setLayoutData(new GridLayout());
		Canvas canvas = createDiagram(shell);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		Display display = shell.getDisplay();
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	private Canvas createDiagram(Composite parent) {
		// Create a root figure and simple layout to contain
		// all other figures
		Figure root = new Figure();
		root.setFont(parent.getFont());
		//XYLayout layout = new XYLayout();
		//root.setLayoutManager(layout);
		
		// Add the father "Andy"
		IFigure andy = createPersonFigure("Andy");
		root.add(andy,new Rectangle(new Point(10, 10), andy.getPreferredSize()));
		//layout.setConstraint(andy,new Rectangle(new Point(10, 10), andy.getPreferredSize()));
		// Add the mother "Betty"
		IFigure betty = createPersonFigure("Betty");
		root.add(betty,new Rectangle(new Point(230, 10), betty.getPreferredSize()));
		//layout.setConstraint(betty,new Rectangle(new Point(230, 10), betty.getPreferredSize()));
		// Add the son "Carl"
		IFigure carl = createPersonFigure("Carl");
		root.add(carl,new Rectangle(new Point(120, 120), carl.getPreferredSize()));
		//layout.setConstraint(carl,new Rectangle(new Point(120, 120), carl.getPreferredSize()));
		root.setLayoutManager(new XYLayout());
		
		// Create a canvas to display the root figure
		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		LightweightSystem lws = new LightweightSystem(canvas);
		lws.setContents(root);
		
		return canvas;
	}
	private IFigure createPersonFigure(String name) {
		RectangleFigure rectangleFigure = new RectangleFigure();
		rectangleFigure.setBackgroundColor(ColorConstants.lightGray);
		rectangleFigure.setLayoutManager(new ToolbarLayout());
		rectangleFigure.setPreferredSize(100, 100);
		rectangleFigure.add(new Label(name));
		return rectangleFigure;
	}
}
