/*
 * $Id: TreeWindow.java,v 1.3 2008/05/27 20:19:04 ahmed Exp $
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import phylosort.TreeNode;
import phylosort.ui.logging.SingleLogger;

/**
 * Modified from <a
 * href="http://java.sun.com/docs/books/tutorial/uiswing/examples/components/DynamicTreeDemoProject/src/components/DynamicTree.java">Dynamic
 * Tree Demo Project - Using Swing Components: Examples</a>.
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.3 $
 */

public class TreeWindow extends JFrame implements MouseListener, ActionListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Frame title
	 */
	private static final String TITLE = "PhyloSort - Reference Taxonomy";

	/**
	 * Default node display
	 */
	private static final String DEFAULT_NODE_DISPLAY = "Node";

	/**
	 * Images location
	 */
	private static final String IMAGES_LOCATION = "/phylosort/ui/images/";

	/**
	 * Command to expand all nodes in the tree
	 */
	private static final String ACTION_COMMAND_EXPAND = "EXPAND";

	/**
	 * Command to collapse all nodes in the tree
	 */
	private static final String ACTION_COMMAND_COLLAPSE = "COLLAPSE";

	/**
	 * Logger
	 */
	private static final Logger logger = SingleLogger.getLogger();

	/**
	 * Root node
	 */
	protected DefaultMutableTreeNode rootNode;

	/**
	 * Default label for the root node
	 */
	private static final String ROOT = "Root";

	/**
	 * Tree model
	 */
	private DefaultTreeModel model;

	/**
	 * {@link JTree}
	 */
	private JTree tree;

	/**
	 * {@link Toolkit}
	 */
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	/**
	 * {@link JToolBar}
	 */
	private JToolBar toolBar = null;

	/**
	 * Button to Expand all nodes
	 */
	private JButton buttonExpand = null;

	/**
	 * Button to collapse all nodes
	 */
	private JButton buttonCollapse = null;

	/**
	 * Nodes selection listener
	 */
	private Set<SelectionListener> listeners = new HashSet<SelectionListener>();

	/**
	 * Constructor
	 * 
	 */
	public TreeWindow() {
		super();

		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		try {
			// Set the icon for the frame
			URL url = getClass().getResource("/phylosort/ui/images/phylosort.png");
			if (url != null) {
				Image image = java.awt.Toolkit.getDefaultToolkit().getImage(url);
				setIconImage(image);
			} else {
				logger.warning("Image URL is NULL");
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "Failed setting the frame image: " + e.getMessage(), e);
		}

		initComponents();
		pack();
	}

	/**
	 * Remove all nodes except the root node.
	 */
	public void clear() {
		rootNode.removeAllChildren();
		model.reload();
	}

	/**
	 * Removes the currently selected node.
	 */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {
				model.removeNodeFromParent(currentNode);
				return;
			}
		}

		// Either there was no selection, or the root was selected.
		toolkit.beep();
	}

	/**
	 * Adds child to the currently selected node.
	 * 
	 * @param child
	 */
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	/**
	 * Adds child to parent node.
	 * 
	 * @param parent
	 *            Parent node.
	 * @param child
	 *            Child object.
	 * @return {@link DefaultMutableTreeNode}
	 */
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	/**
	 * Adds child to parent node.
	 * 
	 * @param parent
	 *            Parent node.
	 * @param child
	 *            Child object.
	 * @param shouldBeVisible
	 *            Visiability
	 * @return {@link DefaultMutableTreeNode}
	 */
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {

		if (parent == null) {
			clear();
			if (child.toString().length() > 0) {
				rootNode.setUserObject(child);
			} else {
				rootNode.setUserObject(ROOT);
			}
			return rootNode;
		}

		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		model.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}

		return childNode;
	}

	/**
	 * Adds a tree selection listener
	 * 
	 * @param selectionListener
	 *            {@link TreeSelectionListener} to be added
	 */
	public void addTreeSelectionListener(TreeSelectionListener selectionListener) {
		tree.addTreeSelectionListener(selectionListener);
	}

	/**
	 * 
	 */
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {

			Point point = event.getPoint();

			TreePath treePath = tree.getPathForLocation(point.x, point.y);

			if (treePath != null) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

				List<String> list = new ArrayList<String>();

				if (node.isLeaf()) {
					list.add(node.getUserObject().toString());
				} else {
					Enumeration<DefaultMutableTreeNode> enumeration = node.depthFirstEnumeration();
					while (enumeration.hasMoreElements()) {
						DefaultMutableTreeNode node2 = enumeration.nextElement();
						if (node2.isLeaf()) {
							list.add(node2.getUserObject().toString());
						}
					}
				}

				for (SelectionListener listener : listeners) {
					listener.selected(list);
				}
			}
		}
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
	public void mousePressed(MouseEvent event) {
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent event) {
	}

	/**
	 * 
	 * 
	 */
	private void initComponents() {

		this.setLayout(new BorderLayout());

		toolBar = new JToolBar();

		buttonExpand = makeButton("expand", ACTION_COMMAND_EXPAND, "Expand all nodes", "Expand");
		toolBar.add(buttonExpand);

		buttonCollapse = makeButton("collapse", ACTION_COMMAND_COLLAPSE, "Collapse all nodes", "Collapse");
		toolBar.add(buttonCollapse);

		add(toolBar, BorderLayout.PAGE_START);

		JPanel treePanel = new JPanel();
		treePanel.setLayout(new GridLayout(1, 0));

		rootNode = new DefaultMutableTreeNode(ROOT);
		model = new DefaultTreeModel(rootNode);

		tree = new JTree(model);
		tree.setEditable(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.setToggleClickCount(3);

		tree.addMouseListener(this);

		JScrollPane scrollPane = new JScrollPane(tree);
		treePanel.add(scrollPane);

		add(treePanel, BorderLayout.CENTER);
	}

	/**
	 * Creates a new button
	 * 
	 * @param imageName
	 *            Image name
	 * @param actionCommand
	 *            Button command name
	 * @param toolTipText
	 *            Tool tip text
	 * @param altText
	 *            Alternative text
	 * @return {@link JButton}
	 */
	protected JButton makeButton(String imageName, String actionCommand, String toolTipText, String altText) {
		// Look for the image.
		String imgLocation = IMAGES_LOCATION + imageName + ".png";
		URL url = getClass().getResource(imgLocation);

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		if (url != null) {
			// Image found
			button.setIcon(new ImageIcon(url, altText));
		} else {
			// Image not found
			button.setText(altText);
			logger.warning("Resource not found: " + imgLocation);
		}

		return button;
	}

	/**
	 * 
	 * @param event
	 *            Action event
	 */
	public void actionPerformed(ActionEvent event) {
		if (ACTION_COMMAND_EXPAND.equalsIgnoreCase(event.getActionCommand())) {
			expandAll();
		} else if (ACTION_COMMAND_COLLAPSE.equalsIgnoreCase(event.getActionCommand())) {
			collapseAll();
		}
	}

	/**
	 * Populates {@link DefaultMutableTreeNode} from {@link TreeNode}
	 * 
	 * @param data
	 *            {@link TreeNode}
	 */
	public void populateTree(TreeNode data) {

		Stack<TreeNode> source = new Stack<TreeNode>();

		Stack<DefaultMutableTreeNode> target = new Stack<DefaultMutableTreeNode>();

		source.add(data);
		target.add(null);

		TreeNode node = null;

		DefaultMutableTreeNode parent = null;

		while (!source.isEmpty()) {

			node = source.pop();
			parent = target.pop();

			String name = null;

			if (node.isLeaf()) {
				name = node.getTaxon();
			} else {
				name = node.getLabel();
			}

			if (name != null) {

				String display = DEFAULT_NODE_DISPLAY;

				if (name.trim().length() > 0) {
					display = name.trim();
				}

				DefaultMutableTreeNode child = this.addObject(parent, display);

				if (!node.isLeaf()) {

					for (TreeNode node2 : node.getChildren()) {
						source.push(node2);
						target.push(child);
					}
				}
			} else {
				logger.warning("Failed parsing a child of: '" + node.getParent().toString() + "'");
			}
		}
	}

	/**
	 * Expands all nodes
	 * 
	 */
	private void expandAll() {

		Stack<DefaultMutableTreeNode> source = new Stack<DefaultMutableTreeNode>();
		Set<DefaultMutableTreeNode> processed = new HashSet<DefaultMutableTreeNode>();

		if (rootNode != null) {

			source.push(rootNode);

			DefaultMutableTreeNode node = null;

			while (!source.isEmpty()) {

				node = source.pop();

				if (!node.isLeaf()) {
					Enumeration childern = node.children();
					while (childern.hasMoreElements()) {
						DefaultMutableTreeNode child = (DefaultMutableTreeNode) childern.nextElement();
						source.push(child);
					}
				} else {

					if (!node.isRoot()) {

						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();

						if (!processed.contains(parent)) {

							TreePath path = new TreePath(parent.getPath());
							tree.expandPath(path);
							processed.add(parent);

						}
					}
				}
			}
		}
	}

	/**
	 * Collapses all nodes
	 * 
	 */
	private void collapseAll() {
		Enumeration<DefaultMutableTreeNode> nodes = rootNode.depthFirstEnumeration();

		while (nodes.hasMoreElements()) {
			DefaultMutableTreeNode node = nodes.nextElement();
			tree.collapsePath(new TreePath(node.getPath()));
		}
	}

	/**
	 * Adds {@link SelectionListener}
	 * 
	 * @param listener
	 *            {@link SelectionListener} to be added
	 */
	public void addSelectionListener(SelectionListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Removes {@link SelectionListener}
	 * 
	 * @param listener
	 *            {@link SelectionListener} to be removed
	 */
	public void removeSelectionListener(SelectionListener listener) {
		if (listener != null && listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
}