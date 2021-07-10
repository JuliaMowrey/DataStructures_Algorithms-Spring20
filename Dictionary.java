/*
* @author Julia Mowrey
* CSC 241_01 Assignment 4
* Professor Hung
*
* The following program accepts a text file (29765.txt) into an AVLTree.
* Then prompts the user to either Search a word or Exit the program
* When a word is enter, a definition of that word is returned
* */
import java.util.*;
import java.io.*;

class Node {
	String key;
	ArrayList<String> definition;
	int height;
	Node left;
  Node right;

	Node(String x) {
		key = x;
		height = 1;
		definition = new ArrayList<String>();
	}
}


class AVLTree {
	Node root;
//USED TO DETERMINEHEIGHT OF AVLTREE
	static int height(Node N) {
		if (N == null)
		{	return 0;}
		return N.height;
	}
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

//USED TO IMPLEMENT AVLTREE ROTATIONS
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		// Perform rotation
		x.right = y;
		y.left = T2;
		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		// Return new root
		return x;
	}
	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		// Perform rotation
		y.left = x;
		x.right = T2;
		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		// Return new root
		return y;
	}

	//MAKE SURE AVLTREE IS BALANCED
	int getBalance(Node N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}


	//GETS LAST NODE in Tree
	static Node getLast(Node node){
		if(node.right==null)
		{
			return node;
		}
		return getLast(node.right);
	}

	//APPENDS DEFINITIONS
	void setDefinition(Node node, String key)
	{
		node.definition.add(key);
	}

//INSERTS NEW NODE INTO AVLTREE - WORD
	Node insert(Node node, String key) {
		/* 1. Perform the normal BST insertion */
		if (node == null)
			return (new Node(key));

		if (key.compareTo(node.key)<0)
			node.left = insert(node.left, key);
		else if (key.compareTo(node.key)>0)
			node.right = insert(node.right, key);
		else // Duplicate keys not allowed
			return node;
		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left), height(node.right));
		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
		int balance = getBalance(node);
		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && key.compareTo(node.left.key)<0)
			return rightRotate(node);
    // Left Right Case
    if (balance > 1 && key.compareTo(node.left.key)>0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }
		// Right Right Case
		if (balance < -1 && key.compareTo(node.right.key)>0)
			return leftRotate(node);
		// Right Left Case
		if (balance < -1 && key.compareTo(node.right.key)<0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		/* return the (unchanged) node pointer */
		return node;
	}


	//FIND THE NODE WITH THE DEFINITION

	static Node getDefnNode(Node node, String WORD)
	{
	  if(node==null || node.key.compareTo(WORD)==0 || node.key.isEmpty())
		{
			return node;
		}
		else if(node.key.compareTo(WORD)<0)
			 {return getDefnNode(node.right,WORD);}
		else
			{return getDefnNode(node.left, WORD);}
	}
}

public class Dictionary
{
	public static void main(String[] args) throws IOException {

		AVLTree tree = new AVLTree();


		String regex = ".*[a-z].*";
		String key;
		String definition=" ";

		Scanner file = new Scanner(new File(args[0]));
		Scanner in = new Scanner(System.in);

		//BEGIN INSERTION
		while(file.hasNext())
		{
				key=file.nextLine();
		 	if(!(key.matches(regex)) && !(key.isEmpty()))
		 	{
					tree.root = tree.insert(tree.root, key);
		 	}
		 	else if(key.contains("Defn:"))
		 	{
			 	definition=key;
				 	while(file.hasNext() && !(key.isEmpty()) && key.matches(regex))
				 	{
							key = file.nextLine();
							definition+="\n"+key;
				 	}
					tree.setDefinition(tree.getLast(tree.root), definition);
			}
		}
	//END OF INSERTION
			System.out.println(tree.height(tree.root));

	//THE FOLLOWING IS FOR USER INTERACTION

			String command;
			String WORD;
			do
			{
				System.out.print("$ ");
				command = in.nextLine();

				if(command.contains("SEARCH"))
				{
					WORD = command.replace("SEARCH ","");
					if(tree.getDefnNode(tree.root,WORD)==null)
					{	System.out.println("WORD does not exist.");}
					else
					{

						for(int i=0; i< tree.getDefnNode(tree.root,WORD).definition.size();i++)
						{
							System.out.println("\n"+tree.getDefnNode(tree.root,WORD).definition.get(i)+"\n");
						}
					}

				}

			 if(!(command.contains("SEARCH") || command.contains("EXIT")))
				{
						System.out.println("Invalid command");
				}
			}while((command.compareTo("EXIT"))!=0);
			System.exit(0);

	}
}
