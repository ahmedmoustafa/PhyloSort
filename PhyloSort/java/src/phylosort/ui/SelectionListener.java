/*
 * $Id: SelectionListener.java,v 1.2 2007/07/26 15:18:45 ahmed Exp $
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

import java.util.List;

/**
 * Simple interface to listen to tree node selection events.
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.2 $
 */
public interface SelectionListener {

	/**
	 * Notify a listener that a item (node) has been selected
	 * 
	 * @param items {@link List} of selected items
	 */
	public void selected(List<String> items);
	
}
