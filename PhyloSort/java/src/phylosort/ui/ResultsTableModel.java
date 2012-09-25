package phylosort.ui;

import javax.swing.table.AbstractTableModel;

public class ResultsTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private final Object[][] data;
	
	/**
	 * 
	 */
	private final String[] headers;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5203875611781125175L;

	public int getColumnCount() {
		return headers.length;
	}

	public int getRowCount() {
		return data.length;
	}
	
	public String getColumnName(int col) {
        return headers[col];
    }

	public Object getValueAt(int i, int j) {
		return data[i][j];
	}

	public ResultsTableModel(Object[][] data, String[] headers) {
		this.data = data;
		this.headers = headers;
	}
}
