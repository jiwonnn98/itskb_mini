package view;

import controller.HospitalController;
import controller.PatientController;
import model.dto.DeptDto;
import model.dto.DiagnosisDto;
import model.dto.DoctorDto;
import model.dto.ReservationDto;
import session.Session;
import session.SessionSet;

import java.util.List;
import java.util.Scanner;

public class MainView {

    private static Scanner sc = new Scanner(System.in);
    private static SessionSet sessionSet = SessionSet.getInstance();

    public static void menu() {
        while (true) {

            MainView.startMenu();
            int menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    // 신규 환자
                    MainView.register();
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

    private static void startMenu() {
        System.out.println("시작메뉴");
        System.out.println("1. 신규 환자 | 2. 로그인 | 9. 종료");
    }

    private static void register() {
        System.out.print("이름 : ");
        String patientName = sc.nextLine();

        System.out.print("주민등록번호 : ");
        String patientSSN = sc.nextLine();

        System.out.print("주소 : ");
        String patientAddr = sc.nextLine();

        System.out.print("휴대폰번호 : ");
        String patientPhone = sc.nextLine();

        PatientController.insertPatient(patientName, patientSSN, patientAddr, patientPhone);
    }

    private static void login() {
        System.out.print("이름 : ");
        String patientName = sc.nextLine();

        System.out.print("주민등록번호 : ");
        String patientSSN = sc.nextLine();

        PatientController.login(patientName, patientSSN);
    }

    public static void logout(int patientSeq) {
        Session session = sessionSet.get(patientSeq);

        sessionSet.remove(session);
    }

    public static void patientMenu(int patientSeq) {
        while (true) {
            Session session = sessionSet.get(patientSeq);
            String patientName = session.getPatientName();

            System.out.println(patientName + "님. 메뉴를 선택해 주세요. ");
            System.out.println("1. 로그아웃 | 2. 예약 하기 | 3. 예약 조회 | 4. 예약 날짜 변경 | 5. 예약 취소 | 6. 진료 조회");

            int menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    logout(patientSeq);
                    return;
                case 2:
                    HospitalController.reservationInsert();
                    break;
                case 3:
                    // TODO 예약 조회
                    break;
                case 4:
                    // TODO 예약 날짜 변경
                    break;
                case 5:
                    // TODO 예약 취소
                    break;
                case 6:
                    // TODO 진료 조회
                    break;
                default:
                    System.out.println("보기에서 메뉴를 선택해 주세요. ");
            }
        }
    }

    public static int deptList(List<DeptDto> deptList) {
        System.out.println("부서 선택");
        for (DeptDto dto : deptList) {
            System.out.println(dto);
        }

        System.out.print("부서 번호를 입력하세요. 예약을 그만두려면 -1을 입력하세요. ");
        int dept = Integer.parseInt(sc.nextLine());

        return dept;
    }

    public static int doctorTimeList(List<DoctorDto> doctorList) {
        System.out.println("의사 선택");
        for (DoctorDto dto : doctorList) {
            System.out.println(dto);
        }

        System.out.println("의사 번호 입력. 예약 그만두려면 -1");
        int doctor = Integer.parseInt(sc.nextLine());

        return doctor;
    }

    public static int reservationTimeArray(int[][] availableTimeArray) {
        System.out.println("가능한 시간 선택");
        int ROW = availableTimeArray.length;
        int COL = availableTimeArray[0].length;

        for (int day = 0; day < ROW; day++) {
            for (int time = 0; time < COL; time++) {
                System.out.printf("%5d", availableTimeArray[day][time]);
            }
            System.out.println();
        }

        System.out.print("날짜를 선택해 주세요. (오늘 0, 내일 1, 모레 2) : ");
        int day = Integer.parseInt(sc.nextLine());
        System.out.print("시간을 선택해 주세요. : ");
        int time = Integer.parseInt(sc.nextLine());

        int timeBlock = availableTimeArray[day][time];

        return timeBlock;
    }

    public static void reservationList(List<ReservationDto> reservationList) {
        for (ReservationDto dto : reservationList) {
            System.out.println(dto);
        }
    }

    public static void diagnosisList(List<DiagnosisDto> diagnosisList) {
        for (DiagnosisDto dto : diagnosisList) {
            System.out.println(dto);
        }
    }
}
