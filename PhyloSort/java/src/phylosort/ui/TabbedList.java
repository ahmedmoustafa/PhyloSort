/*
 * $Id: TabbedList.java,v 1.1.1.1 2007/05/29 16:11:41 ahmed Exp $
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

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * {@link MutableList} in a {@link JTabbedPane}
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.1.1.1 $
 */
public class TabbedList extends JScrollPane {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9190081312999593352L;

	/**
	 * {@link MutableList} inside the tab
	 */
	private final MutableList list;

	/**
	 * Constructor
	 */
	public TabbedList(MutableList target) {
		list = new MutableList(target);
		this.add(list);
		this.setViewportView(list);
	}

	/**
	 * Returns the {@link MutableList} in the {@link JTabbedPane}
	 * 
	 * @return The {@link MutableList} in the {@link JTabbedPane}
	 */
	public MutableList getList() {
		return this.list;
	}
}