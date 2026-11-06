package teamwork02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class UserDatabase {

	ArrayList<User> users = new ArrayList<User>();

	public boolean register(User u) {

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			if (user.getEmail().equals(u.getEmail())) {
				return false;
			}

		}

		users.add(u);

		saveUsers();

		return true;

	}

	public boolean login(String Email, String Password) {

		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);

			if (u.getEmail().equals(Email) &&
					u.getPassword().equals(Password)) {

				return true;

			}

		}
		return false;

	}

	public void saveUsers() {

		try {

			FileWriter fw = new FileWriter("users.csv");
			for (int i = 0; i < users.size(); i++) {
				User u = users.get(i);
				fw.write(u.getUserID() + "," + u.getPassword() + "," + u.getName() + ","
						+ u.getNationality() + "," + u.getEmail() + "\n");
			}
			fw.close();

			System.out.println("Login Ok");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void loadUsers() {

		try {

			FileReader fr = new FileReader("users.csv");
			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {

				String[] datas = line.split(",");

				if (datas.length == 5) {

					User u = new User();

					u.setUserID(datas[0]);

					u.setPassword(datas[1]);
					u.setName(datas[2]);
					u.setNationality(datas[3]);
					u.setEmail(datas[4]);

					users.add(u);
				}
			}

			fr.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
