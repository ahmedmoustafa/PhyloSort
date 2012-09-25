/*
 * $Id: TaxaRegexpExamples.java,v 1.5 2007/07/23 02:30:56 ahmed Exp $
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Examples of regular expression for taxa extraction
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.5 $
 */
public final class TaxaRegexpExamples extends HashMap<String, String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4941186521009996141L;

	/**
	 * Instance
	 */
	private static TaxaRegexpExamples instance = null;

	/**
	 * List of available regular expression examples
	 */
	private List<String> expressions = new ArrayList<String>();

	/**
	 * Returns an instance of {@link TaxaRegexpExamples}
	 * 
	 * @return {@link TaxaRegexpExamples}
	 */
	public static TaxaRegexpExamples getInstance() {

		if (instance == null) {

			instance = new TaxaRegexpExamples();

		}

		return instance;
	}

	/**
	 * Hidden constructor
	 * 
	 */
	private TaxaRegexpExamples() {

		super();

		this.put("(.+)", "Human");
		this.put("([^_]+)_.*", "Human_120407068");
		this.put("([^_]+_[^_]+).*", "Homo_sapiens_120407068");
		this.put(".*\\[([^\\[\\]]+)\\]\\s*$", "120407068 [Homo sapiens]");
		this.put("(\\w+)\\s.*", "Human 120407068");

		for (String expression : this.keySet()) {
			this.expressions.add(expression);
		}
		
		Collections.sort(expressions);
	}

	/**
	 * Returns expression at specific position in the list of examples
	 * 
	 * @param i
	 *            Expression index in the list
	 * @return Expression at specific position in the list of examples
	 */
	public String getExpression(int i) {
		return this.expressions.get(i);
	}

	/**
	 * Returns the description of an expression at specific position in the list
	 * of examples
	 * 
	 * @param i
	 *            Description index in the list
	 * @return Description
	 */
	public String getDescription(int i) {
		return this.getDescription(this.expressions.get(i));
	}

	/**
	 * Returns avaliable of expression examples
	 * 
	 * @return {@link Iterable} of expression
	 */
	public Iterable<String> getExpressions() {
		Set<String> set = this.keySet();

		List<String> sorted = new ArrayList<String>();

		for (String expression : set) {
			sorted.add(expression);
		}

		Collections.sort(sorted);

		return sorted;
	}

	/**
	 * Returns a description for an expression
	 * 
	 * @param expression
	 *            Regular expression (from the examples)
	 * @return {@link String} description of the expression
	 */
	public String getDescription(String expression) {
		return this.get(expression);
	}
}
