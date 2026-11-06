package teamwork02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationDatabase {

	private ArrayList<Application> applications = new ArrayList<Application>();

	public ArrayList<Application> getApplications() {
		return applications;
	}

	public void loadApplications() {

		try {

			FileReader fr = new FileReader("applications.csv");
			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {

				String[] datas = line.split(",");

				Application app = new Application();

				app.setApplicationID(datas[0]);
				app.setUserID(datas[1]);
				app.setJobID(datas[2]);
				app.setApplydate(datas[3]);
				app.setStatus(datas[4]);

				applications.add(app);

			}
			br.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void saveApplications() {

		try {

			FileWriter fw = new FileWriter("applications.csv");

			for (Application app : applications) {

				fw.write(app.getApplicationID() + "," + app.getUserID() + "," + app.getJobID() + ","
						+ app.getApplydate() + "," + app.getStatus() + "\n");
			}
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean applyJob(String userId, String jobId) {

		for (Application app : applications) {

			if (app.getUserID().equals(userId) && app.getJobID().equals(jobId)) {
				return false;
			}

		}

		Application app = new Application();

		String nextId = "";

		if (!applications.isEmpty()) {
			String lastId = applications.get(applications.size() - 1).getApplicationID();

			int id = Integer.parseInt(lastId.substring(1));

			nextId = "A" + String.format("%03d", id + 1);
		}
		app.setApplicationID(nextId);
		app.setUserID(userId);
		app.setJobID(jobId);
		app.setApplydate(LocalDate.now().toString());
		app.setStatus("Pending");

		applications.add(app);

		saveApplications();

		return true;

	}

	public ArrayList<Application> viewApplications(String userId) {

		ArrayList<Application> myapplications = new ArrayList<>();

		for (Application app : applications) {

			if (app.getUserID().equals(userId)) {
				myapplications.add(app);
			}

		}

		return myapplications;

	}

	public boolean updateStatus(String applicationId, String newStatus) {

		for (Application app : applications) {

			if (app.getApplicationID().equals(applicationId)) {

				app.setStatus(newStatus);

				saveApplications();

				return true;
			}
		}

		return false;

	}
}
