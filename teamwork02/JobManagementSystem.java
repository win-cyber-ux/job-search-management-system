package teamwork02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JobManagementSystem {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Scanner scanStr = new Scanner(System.in);

		JobDatabase jobDB = new JobDatabase();

		UserDatabase userDB = new UserDatabase();
		ApplicationDatabase appDB = new ApplicationDatabase();

		jobDB.loadJobs();
		userDB.loadUsers();
		appDB.loadApplications();
		int timeAdmin = 0;
		int timeUser = 0;
		while (true) {

			System.out.println("===== ヒマナビ =====");
			System.out.println("1. 管理者");
			System.out.println("2. ユーザー");
			System.out.println("3. 新規登録");
			System.out.println("0. 終了");
			System.out.print("選択してください: ");

			int menu = scan.nextInt();
			System.out.println("");

			if (menu == 1) {

				System.out.println("Emailを入力してください");
				String mail = scanStr.nextLine();

				System.out.println("Passwordを入力してください");
				String password = scanStr.nextLine();

				boolean result = userDB.login(mail, password);

				if (result) {

					timeAdmin = 0;
					while (true) {

						System.out.println("\n===== 管理者メニュー =====");
						System.out.println("1. 求人を追加");
						System.out.println("2. 求人を更新");
						System.out.println("3. 求人を削除");
						System.out.println("4. 求人一覧");
						System.out.println("5. 求人検索");
						System.out.println("0. ログアウト");
						System.out.print("選択してください: ");

						int admin = scan.nextInt();
						System.out.println();

						if (admin == 1) {

							jobDB.addJob();

						} else if (admin == 2) {

							System.out.print("求人IDを入力してください(Enter job ID): ");
							String id = scanStr.nextLine();
							jobDB.updateJob(id);

						} else if (admin == 3) {

							System.out.print("求人IDを入力してください(Enter job ID): ");
							String id = scanStr.nextLine();
							jobDB.deleteJob(id);

						} else if (admin == 4) {

							jobDB.showAllJobs();

						} else if (admin == 5) {

							System.out.print("検索キーワードを入力してください: ");
							String keyword = scanStr.nextLine();
							jobDB.searchJob(keyword);

						} else if (admin == 0) {

							System.out.println("プログラムを終了します。");

							break;

						} else {

							System.out.println("無効な選択です。");

						}

					}
				} else {
					timeAdmin += 1;
					if (timeAdmin >= 3) {
						System.out.println("Account Locked");

						break;
					}
					System.out.println("password と mail は合ってないです");
					System.out.println("もう一度やり直してください");
					System.out.println();
				}

			} else if (menu == 2) {

				System.out.println("Emailを入力してください");
				String mail = scanStr.nextLine();

				System.out.println("Passwordを入力してください");
				String password = scanStr.nextLine();

				boolean result = userDB.login(mail, password);

				if (result) {
					timeUser = 0;
					while (true) {
						String userId = "";
						String userName = "";
						try {

							FileReader fr = new FileReader("users.csv");
							BufferedReader br = new BufferedReader(fr);

							String line = "";

							while ((line = br.readLine()) != null) {
								String[] data = line.split(",");

								if (data[4].equals(mail)) {

									userId = data[0];
									userName = data[1];
									break;

								}
							}
							br.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

						System.out.println("\n===== ユーザーメニュー =====");
						System.out.println("1. 求人一覧を見る");
						System.out.println("2. 求人を検索する");
						System.out.println("3. 求人に応募する");
						System.out.println("4. 応募履歴を見る");
						System.out.println("0. ログアウト");
						System.out.print("選択してください: ");

						int user = scan.nextInt();

						if (user == 1) {

							jobDB.showAllJobs();

						} else if (user == 2) {

							System.out.print("検索キーワードを入力してください: ");
							String keyword = scanStr.nextLine();
							jobDB.searchJob(keyword);

						} else if (user == 3) {

							System.out.println("job Id を入力してください");
							String jobId = scanStr.nextLine();

							boolean r = appDB.applyJob(userId, jobId);

							if (r) {
								System.out.println("応募しました。");
							} else {
								System.out.println("もう応募しています。");
							}

						} else if (user == 4) {

							ArrayList<Application> userApp = appDB.viewApplications(userId);

							System.out.println("[UserName]:" + userName);

							try {
								FileReader fr = new FileReader("jobs.csv");
								BufferedReader br = new BufferedReader(fr);
								String line = "";

								while ((line = br.readLine()) != null) {
									String[] data = line.split(",");
									for (int i = 0; i < userApp.size(); i++) {

										if (userApp.get(i).getJobID().equals(data[0])) {
											System.out.println("[CompanyName] " + data[1]);
											System.out.println("[Title] " + data[2]);
										}

									}
								}
								br.close();

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else if (user == 0) {
							System.out.println("プログラムを終了します。");

							break;

						} else {

							System.out.println("無効な選択です。");

						}

					}
				} else {
					timeUser += 1;
					if (timeUser >= 3) {
						System.out.println("Account Locked");

						break;
					}
					System.out.println("password と mail は合ってないです");
					System.out.println("もう一度やり直してください");
				}

			} else if (menu == 3) {

				System.out.println("IDを入力してください");
				String id = scanStr.nextLine();

				System.out.println("名前を入力してください");
				String name = scanStr.nextLine();

				System.out.println("passwordを入力してください");
				String password = scanStr.nextLine();

				System.out.println("国籍を入力してください");
				String country = scanStr.nextLine();

				User u = new User();
				u.setUserID(id);
				u.setName(name);
				u.setPassword(password);
				u.setNationality(country);

				while (true) {
					System.out.println("Emailを入力してください");
					String email = scanStr.nextLine();

					if (email.contains("@") && email.contains(".")) {
						u.setEmail(email);
						break;
					} else {
						System.out.println("Emailを正しく入力してください");
					}
				}

				userDB.register(u);

			} else if (menu == 0) {

				System.out.println("終了");
				break;

			} else {

				System.out.println("無効な選択です。");

			}

		}

		scan.close();
		scanStr.close();
	}

}
