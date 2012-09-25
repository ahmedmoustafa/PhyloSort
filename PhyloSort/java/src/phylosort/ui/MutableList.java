/*
 * $Id: MutableList.java,v 1.5 2008/05/28 17:47:32 ahmed Exp $
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package phylosort.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Mutable {@link JList}
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.5 $
 */
public class MutableList extends JList implements MouseListener {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -213214033062865966L;

	/**
	 * Another {@link MutableList} that is a target for moving an element from
	 * this list when this list is double clicked
	 */
	private MutableList target = null;

	/**
	 * 
	 */
	public MutableList() {
		super(new DefaultListModel());
		this.addMouseListener(this);
	}

	/**
	 * 
	 */
	public MutableList(MutableList list) {
		this();
		this.target = list;
	}

	public void setTarget(MutableList list) {
		this.target = list;
	}

	public int getCount() {
		return ((DefaultListModel) this.getModel()).getSize();
	}

	/**
	 * 
	 * @param item
	 */
	public void add(String item) {

		boolean inserted = false;

		for (int i = 0, n = this.getCount(); i < n && !inserted; i++) {
			if (item.compareTo(this.get(i)) < 0) {
				((DefaultListModel) this.getModel()).add(i, item);
				inserted = true;
			}
		}

		if (!inserted) {
			((DefaultListModel) this.getModel()).addElement(item);
		}

	}

	/**
	 * Returns a {@link String} item specified by <code>int<code> position 
	 * 
	 * @param index Item position
	 * 
	 * @return a {@link String} item specified by <code>int<code> position
	 */
	public String get(int index) {
		return (String) ((DefaultListModel) this.getModel()).get(index);
	}

	/**
	 * Removes a item at a specified position
	 * 
	 * @param index
	 *            Item position
	 */
	@Override
	public void remove(int index) {
		((DefaultListModel) this.getModel()).removeElementAt(index);
	}

	/**
	 * Removes all items from the list
	 * 
	 */
	@Override
	public void removeAll() {
		((DefaultListModel) this.getModel()).removeAllElements();
	}

	/**
	 * 
	 */
	public void mousePressed(MouseEvent event) {
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent event) {
	}

	/**
	 * 
	 */
	public void mouseEntered(MouseEvent event) {
	}

	/**
	 * 
	 */
	public void mouseExited(MouseEvent event) {
	}

	/**
	 * 
	 */
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			if (target != null) {
				int index = this.locationToIndex(event.getPoint());
				if (index != -1 && this.target != null) {
					target.add(this.get(index));
					this.remove(index);
				}
			} else {
				System.err.println("No target is selected!");
			}
		}
	}

	/**
	 * 
	 * @param list
	 */
	public void populate(final List<String> list) {
		this.removeAll();

		List<String> sorted = new ArrayList<String>();
		sorted.addAll(list);
		Collections.sort(sorted);

		for (String item : sorted) {
			((DefaultListModel) this.getModel()).addElement(item);
		}
	}

	/**
	 * 
	 * @param itemsToSelect
	 *            {@link List} of {@link String} to be selected
	 */
	public void setSelectedItems(List<String> itemsToSelect) {

		List<Integer> indices = new ArrayList<Integer>();

		for (int i = 0, n = itemsToSelect.size(); i < n; i++) {
			String xtarget = itemsToSelect.get(i);
			boolean found = false;
			for (int j = 0, m = this.getCount(); j < m && !found; j++) {
				String item = this.get(j);
				if (xtarget.equalsIgnoreCase(item)) {
					indices.add(j);
					found = true;
				}
			}
		}

		int array[] = new int[indices.size()];

		for (int i = 0, n = indices.size(); i < n; i++) {
			array[i] = indices.get(i);
		}

		this.setSelectedIndices(array);
	}
}