import java.io.File;   
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;         
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("input.txt");
		if (!file.isFile())
			file = new File("src/input.txt");
		BST<String> objTest = new BST<String>();
		Scanner sc = new Scanner(file);

		while (sc.hasNext()) {
			String operation = sc.next();
			String nodeValue = sc.next();

			if ("INS".equalsIgnoreCase(operation)) {
				objTest.insert(nodeValue);
			} else if ("DEL".equalsIgnoreCase(operation)) {
				System.out.println(objTest.delete(nodeValue)); 
			} else if ("COUNT".equalsIgnoreCase(operation)) {
				if ("NON_LEAF".equalsIgnoreCase(nodeValue)) {
					System.out.println(objTest.getNumberofNonLeaves());
				}

			} else if ("SEARCH".equalsIgnoreCase(operation)) {
				System.out.println(objTest.search(nodeValue));

			} else if ("GET".equalsIgnoreCase(operation)) {
				if ("NON_REC_IN".equalsIgnoreCase(nodeValue)) {
					printTraversal(objTest.inorderNoRecursion());
				} else if ("NON_REC_POST".equalsIgnoreCase(nodeValue)) {
					printTraversal(objTest.postorderNoRecursion());
				}
			}
		}
		sc.close();
	}

	public static void printTraversal(ArrayList arr) {
		System.out.println("");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i));
		}
	}
}