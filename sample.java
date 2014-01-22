/*
Libraries:
JRE System Library[JavaSE-1.7]
postgresql-9.2-1003.jdbc4.jar sample

for Windows: javac sample.java
             java sample

for Linux:
javac sample.java
java -cp .:postgresql-9.2-1003.jdbc4.jar sample

if not compile,please reset the following:
String usr = "rzeng";
String pwd = "Z!ra629166zengruian";
String url = "jdbc:postgresql://postgres.cs.stevens.edu:5432/rzeng";
*/
/*
 subjuct=DBMS
 name : Ruian Zeng
 The algorithm of Report #1 and #2 is similar to Assignment 1's. When I scan the DBMS for the first time, I store 
 all the data into my two-dimension list(imagining two-dimension list contains the 'Vertical' one and the 'Horizontal' one), 
 the 'Vertical' one stores all the 'Horizontal' lists.
  
 */


import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.Format;

public class sample {

	public static void main(String[] args) {
		String usr ="postgres";
		String pwd ="XXXXXX";
		String url ="jdbc:postgresql://localhost:5432/Test";
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			//Report #1
			rs.next();// read the first row
			List<assignment2> Q1v = new ArrayList<assignment2>();//'vertical' List for report #1
			List<assignment2> Q2v = new ArrayList<assignment2>();//'vertical' List for report #2 and #3
			assignment2 Q1h = new assignment2();//'horizontal' List for report #1
			assignment2 Q2h = new assignment2();//'horizontal' List for report #2 and #3

			assignment2 head1, next1, head2, next2;
			head1 = Q1h;
			next1 = Q1h;
			head2 = Q2h;
			next2 = Q2h;

			Vector<String> check1 = new Vector<String>();//using check1 to check whether there are the same customer. if yes, just change the value of its corresponding variables, if no, add new customer to the 'Vertical' list
			Vector<String> check2 = new Vector<String>();//using check2 to check whether there are the same combination of customer and product, if yes, just change the value of its corresponding variables, if no, add new customer and production to the 'Vertical' list 

			// Report #1 initialize
			if (rs.getString(2).equals("Milk")
					|| rs.getString(2).equals("Pepsi")
					|| rs.getString(2).equals("Coke")) {
				Q1h.customer = rs.getString(1);
				Q1h.quantDrinks = rs.getInt(7);
				Q1h.quantFoods = 0;
				Q1h.quantMisc = 0;

			}
			if (rs.getString(2).equals("Bread")
					|| rs.getString(2).equals("Eggs")
					|| rs.getString(2).equals("Fruits")
					|| rs.getString(2).equals("Butter")
					|| rs.getString(2).equals("Cookies")
					|| rs.getString(2).equals("Yogurt")) {
				Q1h.customer = rs.getString(1);
				Q1h.quantDrinks = 0;
				Q1h.quantFoods = rs.getInt(7);
				Q1h.quantMisc = 0;

			}
			if (rs.getString(2).equals("Soap")) {
				Q1h.customer = rs.getString(1);
				Q1h.quantDrinks = 0;
				Q1h.quantFoods = 0;
				Q1h.quantMisc = rs.getInt(7);
			}

			check1.add(Q1h.customer);
			Q1v.add(Q1h);
			int n1 = 0;

			// Report #2 and #3 initialize
			if (rs.getInt(4) >= 1 && rs.getInt(4) <= 3) {
				Q2h.customer = rs.getString(1);
				Q2h.product = rs.getString(2);
				Q2h.TquantQ1 = rs.getInt(7);
				Q2h.TquantQ2 = 0;
				Q2h.TquantQ3 = 0;
				Q2h.TquantQ4 = 0;
				Q2h.numQ1 = 1;
				Q2h.numQ2 = 0;
				Q2h.numQ3 = 0;
				Q2h.numQ4 = 0;

				Q2h.maxQ1 = rs.getInt(7);
				Q2h.maxQ2 = 0;
				Q2h.maxQ3 = 0;
				Q2h.maxQ4 = 0;

				Q2h.countQ1f = 0;
				Q2h.countQ2p = 0;
				Q2h.countQ2f = 0;
				Q2h.countQ3p = 0;
				Q2h.countQ3f = 0;
				Q2h.countQ4p = 0;

			}
			if (rs.getInt(4) >= 4 && rs.getInt(4) <= 6) {
				Q2h.customer = rs.getString(1);
				Q2h.product = rs.getString(2);
				Q2h.TquantQ1 = 0;
				Q2h.TquantQ2 = rs.getInt(7);
				Q2h.TquantQ3 = 0;
				Q2h.TquantQ4 = 0;
				Q2h.numQ1 = 0;
				Q2h.numQ2 = 1;
				Q2h.numQ3 = 0;
				Q2h.numQ4 = 0;

				Q2h.maxQ1 = 0;
				Q2h.maxQ2 = rs.getInt(7);
				Q2h.maxQ3 = 0;
				Q2h.maxQ4 = 0;

				Q2h.countQ1f = 0;
				Q2h.countQ2p = 0;
				Q2h.countQ2f = 0;
				Q2h.countQ3p = 0;
				Q2h.countQ3f = 0;
				Q2h.countQ4p = 0;

			}
			if (rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {
				Q2h.customer = rs.getString(1);
				Q2h.product = rs.getString(2);
				Q2h.TquantQ1 = 0;
				Q2h.TquantQ2 = 0;
				Q2h.TquantQ3 = rs.getInt(7);
				Q2h.TquantQ4 = 0;
				Q2h.numQ1 = 0;
				Q2h.numQ2 = 0;
				Q2h.numQ3 = 1;
				Q2h.numQ4 = 0;

				Q2h.maxQ1 = 0;
				Q2h.maxQ2 = 0;
				Q2h.maxQ3 = rs.getInt(7);
				Q2h.maxQ4 = 0;

				Q2h.countQ1f = 0;
				Q2h.countQ2p = 0;
				Q2h.countQ2f = 0;
				Q2h.countQ3p = 0;
				Q2h.countQ3f = 0;
				Q2h.countQ4p = 0;
			}

			if (rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {
				Q2h.customer = rs.getString(1);
				Q2h.product = rs.getString(2);
				Q2h.TquantQ1 = 0;
				Q2h.TquantQ2 = 0;
				Q2h.TquantQ3 = 0;
				Q2h.TquantQ4 = rs.getInt(7);
				Q2h.numQ1 = 0;
				Q2h.numQ2 = 0;
				Q2h.numQ3 = 0;
				Q2h.numQ4 = 1;

				Q2h.maxQ1 = 0;
				Q2h.maxQ2 = 0;
				Q2h.maxQ3 = 0;
				Q2h.maxQ4 = rs.getInt(7);

				Q2h.countQ1f = 0;
				Q2h.countQ2p = 0;
				Q2h.countQ2f = 0;
				Q2h.countQ3p = 0;
				Q2h.countQ3f = 0;
				Q2h.countQ4p = 0;

			}
			Q2v.add(Q2h);
			check2.add(Q2h.customer + Q2h.product);
			int n2 = 0;

			while (rs.next()) {

				if (check1.contains(rs.getString(1))) {//if the customer has already exists in the list, find its index in the list and change its corresponding variables
					while (!next1.customer.contentEquals(rs.getString(1))) {
						next1 = Q1v.get(n1);
						n1++;
					}

					if (rs.getString(2).equals("Milk")
							|| rs.getString(2).equals("Pepsi")
							|| rs.getString(2).equals("Coke"))
						next1.quantDrinks += rs.getInt(7);
					if (rs.getString(2).equals("Bread")
							|| rs.getString(2).equals("Eggs")
							|| rs.getString(2).equals("Fruits")
							|| rs.getString(2).equals("Butter")
							|| rs.getString(2).equals("Cookies")
							|| rs.getString(2).equals("Yogurt"))
						next1.quantFoods += rs.getInt(7);
					if (rs.getString(2).equals("Soap"))
						next1.quantMisc += rs.getInt(7);
					next1 = head1;
					n1 = 0;
				}

				else {
					Q1h = new assignment2();//if this customer hasn't existed in the list, create a new 'horizontal' list to store this 'new' customer and then add to the 'vertical' list and check1 vector.
					if (rs.getString(2).equals("Milk")
							|| rs.getString(2).equals("Pepsi")
							|| rs.getString(2).equals("Coke")) {
						Q1h.customer = rs.getString(1);
						Q1h.quantDrinks = rs.getInt(7);
						Q1h.quantFoods = 0;
						Q1h.quantMisc = 0;
					}
					if (rs.getString(2).equals("Bread")
							|| rs.getString(2).equals("Eggs")
							|| rs.getString(2).equals("Fruits")
							|| rs.getString(2).equals("Butter")
							|| rs.getString(2).equals("Cookies")
							|| rs.getString(2).equals("Yogurt")) {
						Q1h.customer = rs.getString(1);
						Q1h.quantDrinks = 0;
						Q1h.quantFoods = rs.getInt(7);
						Q1h.quantMisc = 0;
					}
					if (rs.getString(2).equals("Soap")) {
						Q1h.customer = rs.getString(1);
						Q1h.quantDrinks = 0;
						Q1h.quantFoods = 0;
						Q1h.quantMisc = rs.getInt(7);
					}

					Q1v.add(Q1h);
					check1.add(Q1h.customer);
				}

				//Report #2
				if (check2.contains(rs.getString(1) + rs.getString(2))) {//if the customer and product have already exist in the list, find its index in the list and change its corresponding variables
					while (!(next2.customer.contentEquals(rs.getString(1)) && next2.product
							.contentEquals(rs.getString(2)))) {
						next2 = Q2v.get(n2);
						n2++;
					}
					if (rs.getInt(4) >= 1 && rs.getInt(4) <= 3) {
						next2.TquantQ1 += rs.getInt(7);
						next2.numQ1++;

						if (next2.maxQ1 < rs.getInt(7))
							next2.maxQ1 = rs.getInt(7);

					}
					if (rs.getInt(4) >= 4 && rs.getInt(4) <= 6) {
						next2.TquantQ2 += rs.getInt(7);
						next2.numQ2++;

						if (next2.maxQ2 < rs.getInt(7))
							next2.maxQ2 = rs.getInt(7);
					}
					if (rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {
						next2.TquantQ3 += rs.getInt(7);
						next2.numQ3++;
						if (next2.maxQ3 < rs.getInt(7))
							next2.maxQ3 = rs.getInt(7);

					}
					if (rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {
						next2.TquantQ4 += rs.getInt(7);
						next2.numQ4++;

						if (next2.maxQ4 < rs.getInt(7))
							next2.maxQ4 = rs.getInt(7);
					}
					next2 = head2;
					n2 = 0;
				} else {//if this customer and product haven't existed in the list, create a new 'horizontal' list to store this 'new' customer and product and then add to the 'vertical' list and check2 vector.
					Q2h = new assignment2();
					if (rs.getInt(4) >= 1 && rs.getInt(4) <= 3) {
						Q2h.customer = rs.getString(1);
						Q2h.product = rs.getString(2);
						Q2h.TquantQ1 = rs.getInt(7);
						Q2h.TquantQ2 = 0;
						Q2h.TquantQ3 = 0;
						Q2h.TquantQ4 = 0;
						Q2h.numQ1 = 1;
						Q2h.numQ2 = 0;
						Q2h.numQ3 = 0;
						Q2h.numQ4 = 0;

						Q2h.maxQ1 = rs.getInt(7);
						Q2h.maxQ2 = 0;
						Q2h.maxQ3 = 0;
						Q2h.maxQ4 = 0;

					}
					if (rs.getInt(4) >= 4 && rs.getInt(4) <= 6) {
						Q2h.customer = rs.getString(1);
						Q2h.product = rs.getString(2);
						Q2h.TquantQ1 = 0;
						Q2h.TquantQ2 = rs.getInt(7);
						Q2h.TquantQ3 = 0;
						Q2h.TquantQ4 = 0;
						Q2h.numQ1 = 0;
						Q2h.numQ2 = 1;
						Q2h.numQ3 = 0;
						Q2h.numQ4 = 0;

						Q2h.maxQ1 = 0;
						Q2h.maxQ2 = rs.getInt(7);
						Q2h.maxQ3 = 0;
						Q2h.maxQ4 = 0;
					}
					if (rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {
						Q2h.customer = rs.getString(1);
						Q2h.product = rs.getString(2);
						Q2h.TquantQ1 = 0;
						Q2h.TquantQ2 = 0;
						Q2h.TquantQ3 = rs.getInt(7);
						Q2h.TquantQ4 = 0;
						Q2h.numQ1 = 0;
						Q2h.numQ2 = 0;
						Q2h.numQ3 = 1;
						Q2h.numQ4 = 0;

						Q2h.maxQ1 = 0;
						Q2h.maxQ2 = 0;
						Q2h.maxQ3 = rs.getInt(7);
						Q2h.maxQ4 = 0;
					}

					if (rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {
						Q2h.customer = rs.getString(1);
						Q2h.product = rs.getString(2);
						Q2h.TquantQ1 = 0;
						Q2h.TquantQ2 = 0;
						Q2h.TquantQ3 = 0;
						Q2h.TquantQ4 = rs.getInt(7);
						Q2h.numQ1 = 0;
						Q2h.numQ2 = 0;
						Q2h.numQ3 = 0;
						Q2h.numQ4 = 1;

						Q2h.maxQ1 = 0;
						Q2h.maxQ2 = 0;
						Q2h.maxQ3 = 0;
						Q2h.maxQ4 = rs.getInt(7);
					}
					check2.add(Q2h.customer + Q2h.product);
					Q2v.add(Q2h);

				}

			}
             //report #3
			/* report #3 needs six variables, Q1 needs one(after); Q2 needs two(before and after); 
			  Q3 needs two(before and after); Q4 needs one(before). note:Q1's 'after' is different from Q3's 'before'*/
			next2 = head2;
			rs = stmt.executeQuery("SELECT * FROM Sales"); 
        
			int avgQ1, avgQ2, avgQ3, avgQ4, n3 = 0;

			while (rs.next()) {
				String h1 = rs.getString(1);
				String h2 = rs.getString(2);

				while (!(Q2v.get(n3).customer.contentEquals(rs.getString(1)) && Q2v
						.get(n3).product.contentEquals(rs.getString(2)))) {

					n3++;
				}
 
				if (Q2v.get(n3).numQ1 == 0)
					avgQ1 = 0;
				else
					avgQ1 = Q2v.get(n3).TquantQ1 / Q2v.get(n3).numQ1;

				if (Q2v.get(n3).numQ2 == 0)
					avgQ2 = 0;
				else
					avgQ2 = Q2v.get(n3).TquantQ2 / Q2v.get(n3).numQ2;

				if (Q2v.get(n3).numQ3 == 0)
					avgQ3 = 0;
				else
					avgQ3 = Q2v.get(n3).TquantQ3 / Q2v.get(n3).numQ3;

				if (Q2v.get(n3).numQ4 == 0)
					avgQ4 = 0;
				else
					avgQ4 = Q2v.get(n3).TquantQ4 / Q2v.get(n3).numQ4;

				if (rs.getInt(4) >= 1 && rs.getInt(4) <= 3) { // when the scanned time belongs to Q1, compare the current quantity to average of Q2 and maximum value of Q2
					if (rs.getInt(7) >= avgQ2
							&& rs.getInt(7) <= Q2v.get(n3).maxQ2)
						Q2v.get(n3).countQ2p++;

				}
				if (rs.getInt(4) >= 4 && rs.getInt(4) <= 6) { // when the scanned time belongs to Q2, compare the current quantity to average of Q1,Q3 and maximum value of Q1,Q3
					if (rs.getInt(7) >= avgQ1
							&& rs.getInt(7) <= Q2v.get(n3).maxQ1)
						Q2v.get(n3).countQ1f++;
					if (rs.getInt(7) >= avgQ3
							&& rs.getInt(7) <= Q2v.get(n3).maxQ3)
						Q2v.get(n3).countQ3p++;
				}
				if (rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {// when the scanned time belongs to Q3, compare the current quantity to average of Q2,Q4 and maximum value of Q2,Q4
					if (rs.getInt(7) >= avgQ2
							&& rs.getInt(7) <= Q2v.get(n3).maxQ2)
						Q2v.get(n3).countQ2f++;
					if (rs.getInt(7) >= avgQ4
							&& rs.getInt(7) <= Q2v.get(n3).maxQ4)
						Q2v.get(n3).countQ4p++;
				}
				if (rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {// when the scanned time belongs to Q4, compare the current quantity to average of Q3 and maximum value of Q3
					int a = rs.getInt(7);
					int h = Q2v.get(n3).maxQ3;
					if (rs.getInt(7) >= avgQ3
							&& rs.getInt(7) <= Q2v.get(n3).maxQ3)
						Q2v.get(n3).countQ3f++;

				}
				n3 = 0;

			}

			// Report #1
			// print the Report #1 out
			System.out.println(String.format("%-9s", "CUSTOMER")
					+ String.format("%-7s", "DRINKS")
					+ String.format("%-7s", "FOOD")
					+ String.format("%-7s", "MISC"));

			System.out.println(String.format("%-9s", "--------")
					+ String.format("%-7s", "------")
					+ String.format("%-7s", "------")
					+ String.format("%-7s", "------"));

			int printnum = 0;
			while (printnum < Q1v.size()) {
				System.out.println(String.format("%-9s",
						Q1v.get(printnum).customer)
						+ String.format("%-7d", Q1v.get(printnum).quantDrinks)
						+ String.format("%-7d", Q1v.get(printnum).quantFoods)
						+ String.format("%-7d", Q1v.get(printnum).quantMisc));
				printnum++;
			}
			System.out.println();

			// Report #2
			// print the Report #2 out
			System.out.println(String.format("%-9s", "CUSTOMER")
					+ String.format("%-8s", "PRODUCT")
					+ String.format("%-8s", "QUARTER")
					+ String.format("%-11s", "BEFORE_AVG")
					+ String.format("%-10s", "AFTER_AVG"));

			System.out.println(String.format("%-9s", "--------")
					+ String.format("%-8s", "-------")
					+ String.format("%-8s", "-------")
					+ String.format("%-11s", "----------")
					+ String.format("%-10s", "---------"));
			printnum = 0;
			while (printnum < Q2v.size()) {
				if (Q2v.get(printnum).numQ1 == 0)
					avgQ1 = 0;
				else
					avgQ1 = Q2v.get(printnum).TquantQ1
							/ Q2v.get(printnum).numQ1;

				if (Q2v.get(printnum).numQ2 == 0)
					avgQ2 = 0;
				else
					avgQ2 = Q2v.get(printnum).TquantQ2
							/ Q2v.get(printnum).numQ2;

				if (Q2v.get(printnum).numQ3 == 0)
					avgQ3 = 0;
				else
					avgQ3 = Q2v.get(printnum).TquantQ3
							/ Q2v.get(printnum).numQ3;

				if (Q2v.get(printnum).numQ4 == 0)
					avgQ4 = 0;
				else
					avgQ4 = Q2v.get(printnum).TquantQ4
							/ Q2v.get(printnum).numQ4;

				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q1")
						+ String.format("%-11s", "<NULL>")
						+ String.format("%-10d", avgQ2));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q2")
						+ String.format("%-11d", avgQ1)
						+ String.format("%-10d", avgQ3));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q3")
						+ String.format("%-11d", avgQ2)
						+ String.format("%-10d", avgQ4));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q4")
						+ String.format("%-11d", avgQ3)
						+ String.format("%-10s", "<NULL>"));

				printnum++;
			}

			System.out.println();
			
			// Report #3
	    	// print the Report #3 out
			System.out.println(String.format("%-9s", "CUSTOMER")
					+ String.format("%-8s", "PRODUCT")
					+ String.format("%-8s", "QUARTER")
					+ String.format("%-11s", "BEFORE_TOT")
					+ String.format("%-10s", "AFTER_TOT"));

			System.out.println(String.format("%-9s", "--------")
					+ String.format("%-8s", "-------")
					+ String.format("%-8s", "-------")
					+ String.format("%-11s", "----------")
					+ String.format("%-10s", "---------"));

			printnum = 0;
			while (printnum < Q2v.size()) {
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q1")
						+ String.format("%-11s", "<NULL>")
						+ String.format("%-10d", Q2v.get(printnum).countQ1f));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q2")
						+ String.format("%-11d", Q2v.get(printnum).countQ2p)
						+ String.format("%-10d", Q2v.get(printnum).countQ2f));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q3")
						+ String.format("%-11d", Q2v.get(printnum).countQ3p)
						+ String.format("%-10d", Q2v.get(printnum).countQ3f));
				System.out.println(String.format("%-9s",
						Q2v.get(printnum).customer)
						+ String.format("%-8s", Q2v.get(printnum).product)
						+ String.format("%-8s", "Q4")
						+ String.format("%-11d", Q2v.get(printnum).countQ4p)
						+ String.format("%-10s", "<NULL>"));
				printnum++;
			}
		} catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
