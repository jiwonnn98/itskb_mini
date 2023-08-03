package view;

import controller.PatientController;
import session.SessionSet;

import java.util.Scanner;

public class MainView {

    private static Scanner sc = new Scanner(System.in);

    // TODO : 초기 메뉴 1. 신규 환자 | 2. 로그인 | 9. 종료
    public static void menu() {
        SessionSet sessionSet = SessionSet.getInstance();
        while (true) {

            MainView.startMenu();
            int menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    // 신규 환자
                    break;
                case 2:
                    // 로그인
                    MainView.login();
                    break;
                case 9:
                    // 종료
                    System.out.println("==================== 프로그램을 종료합니다. ====================");
                    System.exit(0);
                default:
                    System.out.println("다시 입력하세요.");
            }
        }
    }

    private static void login() {
        System.out.print("이름 : ");
        String patientName = sc.nextLine();

        System.out.print("주민등록번호 : ");
        String patientSSN = sc.nextLine();

        PatientController.login(patientName, patientSSN);
    }

    private static void startMenu() {
        System.out.println("시작메뉴");
        System.out.println("1. 신규 환자 | 2. 로그인 | 9. 종료");
    }

    // TODO : 로그인 성공 시 메뉴 1. 로그아웃 | 2. 예약 하기 | 3. 예약 조회 | 4. 예약 날짜 변경 | 5. 예약 취소 | 6. 진료 조회

    // TODO : 로그아웃

    // TODO : 예약 하기

    // TODO : 예약 조회

    // TODO : 예약 날짜 변경

    // TODO : 예약 취소

    // TODO : 진료 조회

}
