package test.handlers;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println(event.getParameters().toString());
		Collection collection = HandlerUtil.getActiveContexts(event);
		ISelection selection = HandlerUtil.getShowInSelectionChecked(event);
		Iterator coll = ((TreeSelection) selection).iterator();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		//o/p:C:\Users\Sankhajit\runtime-EclipseApplication
		System.out.println(workspace.getRoot().getLocation().toOSString());
		String strWorkspacePath = ResourcesPlugin.getWorkspace().getRoot().toString();
		while(coll.hasNext()){
			org.eclipse.jdt.internal.core.CompilationUnit strSelectedFileData = (org.eclipse.jdt.internal.core.CompilationUnit) coll.next();
			String selectedFilename = strSelectedFileData.getElementName();
			try {
				IResource resource = strSelectedFileData.getCorrespondingResource();
				IPath path = resource.getFullPath();
				//o/p:/testPro/src/testPro/Test.java
				System.out.println(path.toString());
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//IJavaProject project = strSelectedFileData.getJavaProject();
			System.out.println(selectedFilename);
		}
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Test",
				"Hello, Eclipse world");
		return null;
	}
}
