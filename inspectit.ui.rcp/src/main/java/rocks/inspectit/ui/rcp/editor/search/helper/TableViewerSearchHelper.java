package rocks.inspectit.ui.rcp.editor.search.helper;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;

import rocks.inspectit.ui.rcp.editor.table.input.TableInputController;
import rocks.inspectit.ui.rcp.repository.RepositoryDefinition;

/**
 * Search helper for {@link TableViewer}.
 * 
 * @author Ivan Senic
 * @see AbstractSearchHelper
 * 
 */
public class TableViewerSearchHelper extends AbstractSearchHelper {

	/**
	 * {@link TableViewer}.
	 */
	private final TableViewer tableViewer;

	/**
	 * {@link TableInputController}.
	 */
	private final TableInputController tableInputController;

	/**
	 * @param tableViewer
	 *            {@link TableViewer}.
	 * @param tableInputController
	 *            {@link TableInputController}.
	 * @param repositoryDefinition
	 *            {@link RepositoryDefinition}. Needed for
	 *            {@link rocks.inspectit.ui.rcp.editor.search.factory.SearchFactory}.
	 */
	public TableViewerSearchHelper(TableViewer tableViewer, TableInputController tableInputController, RepositoryDefinition repositoryDefinition) {
		super(repositoryDefinition);
		this.tableViewer = tableViewer;
		this.tableInputController = tableInputController;
		for (TableColumn tableColumn : tableViewer.getTable().getColumns()) {
			tableColumn.addSelectionListener(getColumnSortingListener());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectElement(Object element) {
		StructuredSelection ss = new StructuredSelection(element);
		tableViewer.setSelection(ss, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getAllObjects() {
		return tableInputController.getObjectsToSearch(tableViewer.getInput());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StructuredViewer getViewer() {
		return tableViewer;
	}
}
