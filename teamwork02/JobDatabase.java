package teamwork02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class JobDatabase {

	Scanner scan = new Scanner(System.in);
	Scanner scanStr = new Scanner(System.in);

	private ArrayList<Job> jobs = new ArrayList<>();

	public void loadJobs() {

		try {
			FileReader fr = new FileReader("jobs.csv");

			BufferedReader br = new BufferedReader(fr);

			String line = "";

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");

				Job j = new Job();
				j.setJobId(data[0]);
				j.setCompany(data[1]);
				j.setTitle(data[2]);
				j.setLocation(data[3]);
				j.setSalary(Double.parseDouble(data[4]));
				j.setType(data[5]);
				j.setSkill(data[6]);
				j.setDeadline(data[7]);
				j.setDescription(data[8]);

				jobs.add(j);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean checkDuplicate(String jobId) {

		for (int i = 0; i < jobs.size(); i++) {

			if (jobId.equals(jobs.get(i).getJobId())) {
				return true;
			}

		}

		return false;
	}

	public void saveJobs() {

		try {

			FileWriter fw = new FileWriter("jobs.csv");

			for (int i = 0; i < jobs.size(); i++) {

				Job j = jobs.get(i);

				fw.write(
						j.getJobId() + "," +
								j.getCompany() + "," +
								j.getTitle() + "," +
								j.getLocation() + "," +
								j.getSalary() + "," +
								j.getType() + "," +
								j.getSkill() + "," +
								j.getDeadline() + "," +
								j.getDescription() + "\n");

			}

			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addJob() {

		Job j = new Job();
		System.out.println("現在の求人ID: " + getLastJobId() + "までです");
		while (true) {

			System.out.print("JobID を入力してください (J...): ");
			String id = scanStr.nextLine();

			if (!id.startsWith("J")) {
				System.out.println("ID start with J");
				continue;
			}

			if (checkDuplicate(id)) {
				System.out.println("Same Job ID");
				continue;
			}

			j.setJobId(id);
			break;
		}
		System.out.print("会社名を入力してください: ");
		j.setCompany(scanStr.nextLine());

		System.out.print("タイトルを入力してください: ");
		j.setTitle(scanStr.nextLine());

		System.out.print("勤務地を入力してください: ");
		j.setLocation(scanStr.nextLine());

		System.out.print("給料を入力してください: ");
		String salary = scanStr.nextLine();

		if (salary.contains("万")) {
			salary = salary.replace("万", "");
			j.setSalary(Double.parseDouble(salary) * 10000);

		} else if (salary.contains("千")) {
			salary = salary.replace("千", "");
			j.setSalary(Double.parseDouble(salary) * 1000);

		} else {
			j.setSalary(Double.parseDouble(salary));
		}
		System.out.print("種類(Type)を入力してください: ");
		j.setType(scanStr.nextLine());

		System.out.print("必要スキルを入力してください: ");
		j.setSkill(scanStr.nextLine());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

		while (true) {
			System.out.print("締切日を入力してください (yyyy/M/d): ");
			String deadline = scanStr.nextLine();

			try {
				LocalDate inputDate = LocalDate.parse(deadline, formatter);

				if (inputDate.isBefore(LocalDate.now())) {
					System.out.println("締切日は今日以降の日付を入力してください。");
				} else {
					j.setDeadline(deadline);
					break;
				}

			} catch (Exception e) {
				System.out.println("日付の形式が正しくありません。(例: 2026/8/6)");
			}
		}

		System.out.print("説明を入力してください: ");
		j.setDescription(scanStr.nextLine());

		jobs.add(j);
		saveJobs();

		System.out.println("Job added successfully.");

	}

	public void updateJob(String jobId) {

		int choice = 0;

		for (int i = 0; i < jobs.size(); i++) {

			if (jobId.equals(jobs.get(i).getJobId())) {
				System.out.println("会社名を変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("会社名を入力してください: ");
					jobs.get(i).setCompany(scanStr.nextLine());
				}

				System.out.println("タイトルを変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("タイトルを入力してください: ");
					jobs.get(i).setTitle(scanStr.nextLine());
				}

				System.out.println("勤務地を変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("勤務地を入力してください: ");
					jobs.get(i).setLocation(scanStr.nextLine());
				}
				System.out.println("給料を変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("給料を入力してください: ");
					String salary = scanStr.nextLine();
					if (salary.contains("万")) {
						salary = salary.replace("万", "");
						jobs.get(i).setSalary(Double.parseDouble(salary) * 10000);

					} else if (salary.contains("千")) {
						salary = salary.replace("千", "");
						jobs.get(i).setSalary(Double.parseDouble(salary) * 1000);

					} else {
						jobs.get(i).setSalary(Double.parseDouble(salary));
					}
				}
				System.out.println("種類(Type)を変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("種類(Type)を入力してください: ");
					jobs.get(i).setType(scanStr.nextLine());
				}

				System.out.println("必要スキルを変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("必要スキルを入力してください: ");
					jobs.get(i).setSkill(scanStr.nextLine());
				}
				System.out.println("締切日をを変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();

				if (choice == 1) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

					while (true) {
						System.out.print("締切日を入力してください (yyyy/M/d): ");
						String deadline = scanStr.nextLine();

						try {
							LocalDate inputDate = LocalDate.parse(deadline, formatter);

							if (inputDate.isBefore(LocalDate.now())) {
								System.out.println("締切日は今日以降の日付を入力してください。");
							} else {
								jobs.get(i).setDeadline(deadline);
								break;
							}

						} catch (Exception e) {
							System.out.println("日付の形式が正しくありません。(例: 2026/8/6)");
						}
					}

				}

				System.out.println("説明を変更しますか？");
				System.out.println("1:yes 2:no");
				choice = scan.nextInt();

				if (choice == 1) {
					System.out.print("説明を入力してください: ");
					jobs.get(i).setDescription(scanStr.nextLine());
				}

				saveJobs();
				System.out.println("Job updated successfully.");
				break;

			}

		}

	}

	public void deleteJob(String jobId) {

		boolean found = false;

		for (int i = 0; i < jobs.size(); i++) {

			if (jobId.equals(jobs.get(i).getJobId())) {

				jobs.remove(i);

				saveJobs();

				System.out.println("Job deleted successfully.");

				found = true;

				break;
			}
		}

		if (!found) {
			System.out.println("Job ID not found.");
		}

	}

	public void searchJob(String keyword) {

		boolean found = false;

		for (int i = 0; i < jobs.size(); i++) {

			Job j = jobs.get(i);

			if (j.getCompany().contains(keyword) ||
					j.getTitle().contains(keyword) ||
					j.getLocation().contains(keyword) ||
					j.getType().contains(keyword) ||
					j.getSkill().contains(keyword)) {

				System.out.println("----------------------------");
				System.out.println("Job ID : " + j.getJobId());
				System.out.println("Company : " + j.getCompany());
				System.out.println("Title : " + j.getTitle());
				System.out.println("Location : " + j.getLocation());
				System.out.println("Salary : " + j.getSalary());
				System.out.println("Type : " + j.getType());
				System.out.println("Skill : " + j.getSkill());
				System.out.println("Deadline : " + j.getDeadline());
				System.out.println("Description : " + j.getDescription());

				found = true;
			}
		}

		if (!found) {
			System.out.println("No job found.");
		}
	}

	public void showAllJobs() {

		if (jobs.isEmpty()) {
			System.out.println("No jobs available.");
			return;
		}

		for (int i = 0; i < jobs.size(); i++) {

			Job j = jobs.get(i);

			System.out.println("----------------------------");
			System.out.println("Job ID : " + j.getJobId());
			System.out.println("Company : " + j.getCompany());
			System.out.println("Title : " + j.getTitle());
			System.out.println("Location : " + j.getLocation());
			System.out.println("Salary : " + j.getSalary());
			System.out.println("Type : " + j.getType());
			System.out.println("Skill : " + j.getSkill());
			System.out.println("Deadline : " + j.getDeadline());
			System.out.println("Description : " + j.getDescription());
		}
	}

	public String getLastJobId() {

		if (jobs.isEmpty()) {
			return "J000";
		}

		return jobs.get(jobs.size() - 1).getJobId();
	}

}
