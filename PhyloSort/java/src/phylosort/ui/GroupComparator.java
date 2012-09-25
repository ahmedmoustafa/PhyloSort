/*
 * $Id: GroupComparator.java,v 1.2 2008/05/23 06:32:58 ahmed Exp $
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A comparator between groups by comparing the groups names.
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.2 $
 */
public class GroupComparator implements Comparator<List<String>> {

	/**
	 * Compares between two subtrees based on their branch lengths.
	 * 
	 * @param list1
	 *            The first list
	 * @param list2
	 *            The second list
	 * 
	 * @return 0 if <code>list1</code> = <code>list2</code>, 1 if <code>list1</code>
	 *         &gt; <code>list2</code>, or -1 if <code>list2</code> &lt;
	 *         <code>list2</code>
	 */
	public int compare(List<String> list1, List<String> list2) {
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		int size1 = list1.size();
		int size2 = list2.size();
		
		if (size1 > size2) {
			return -1;
		} else if (size1 < size2) {
			return 1;
		} else {
			if (size1 == 0 || size2 == 0) {
				// Both should be zeros here anyway.
				return 0;
			} else {
				for (int i = 0; i < size1; i++) {
					int val = list1.get(i).compareTo(list2.get(i));
					if (val != 0) {
						return val;
					}
				}
				return 0; 
			}
		}
	}
}