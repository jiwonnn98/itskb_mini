package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import controller.HospitalController;
import controller.PatientController;
import model.dto.DeptDto;
import model.dto.DoctorDto;
import model.dto.ReservationDto;
import session.Session;
import session.SessionSet;

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
                    System.out.println("===================== 프로그램을 종료합니다. =====================");
                    System.exit(0);
                default:
                    System.out.println("다시 입력하세요.");
            }
        }
    }

    private static void startMenu() {
        System.out.println("==================== KB 종합 병원 예약 시스템 ====================");
        System.out.println("1. 신규 환자 | 2. 로그인 | 9. 종료");
        System.out.println("==================================================================");
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

            System.out.println();
            System.out.println(patientName + "님. 메뉴를 선택해 주세요. ");

            System.out.println("============================== Menu ==============================");
            System.out.println("1. 로그아웃 | 2. 예약 하기 | 3. 예약 조회");
            System.out.println("4. 예약 날짜 변경 | 5. 예약 취소 | 6. 진료 조회");
            System.out.println("==================================================================");

            int menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    logout(patientSeq);
                    return;
                case 2:
                    List<DeptDto> deptList = HospitalController.deptList();
                    int deptSeq = deptList(deptList);
                    if (deptSeq == -1) {
                        System.out.println("예약을 중단합니다. ");
                        break;
                    }
                    List<DoctorDto> docList = HospitalController.docList(deptSeq);
                    int doctorSeq = doctorTimeList(docList);
                    if (doctorSeq == -1) {
                        System.out.println("예약을 중단합니다. ");
                        break;
                    }
                    int[][] timeArr = HospitalController.timeList(doctorSeq);
                    int[] selectTime = reservationTimeArray(timeArr);
                    if (selectTime[0] == -1 || selectTime[1] == -1) {
                        System.out.println("예약을 중단합니다. ");
                        break;
                    }
                    HospitalController.reservation(selectTime[0], selectTime[1], patientSeq, timeArr[selectTime[0]][0]);
                    break;
                case 3: // 예약 조회
                    HospitalController.reservationList(patientSeq);
                    break;
                case 4: // 예약 변경
                    HospitalController.reservationList(patientSeq);
                    int reservSeq = reservationSeqUpdate();
                    List<DeptDto> deptListUpdate = HospitalController.deptList();
                    int deptSeqUpdate = deptList(deptListUpdate);
                    if (deptSeqUpdate == -1) {
                        System.out.println("예약 변경을 중단합니다. ");
                        break;
                    }
                    List<DoctorDto> docListUpdate = HospitalController.docList(deptSeqUpdate);
                    int doctorSeqUpdate = doctorTimeList(docListUpdate);
                    if (doctorSeqUpdate == -1) {
                        System.out.println("예약 변경을 중단합니다. ");
                        break;
                    }
                    int[][] timeArrUpdate = HospitalController.timeList(doctorSeqUpdate);
                    int[] selectTimeUpdate = reservationTimeArray(timeArrUpdate);
                    if (selectTimeUpdate[0] == -1 || selectTimeUpdate[1] == -1) {
                        System.out.println("예약 변경을 중단합니다. ");
                        break;
                    }
                    ReservationDto reserv = new ReservationDto(reservSeq, LocalDate.now().plusDays(selectTimeUpdate[0]).toString(), patientSeq, timeArrUpdate[selectTimeUpdate[0]][0], selectTimeUpdate[1]);
                    HospitalController.reservationModifyDate(reserv);
                    break;
                case 5: // 예약 취소
                    HospitalController.reservationList(patientSeq);
                    int reserveSeq = reservationSeq();
                    HospitalController.reservationCancel(reserveSeq);
                    break;
                case 6: // 진료 조회
                    HospitalController.diagnosisList(patientSeq);
                    break;
                default:
                    System.out.println("보기에서 메뉴를 선택해 주세요. ");
            }
        }
    }


    public static int deptList(List<DeptDto> deptList) {
        System.out.println();
        System.out.println("--------------- 부서 선택창 --------------------");

        System.out.println("+--------------+--------------------------------+");
        System.out.printf("| %-2 | %-30s |\n", "부서 번호", "부서 이름");
        System.out.println("+--------------+--------------------------------+");

        for (DeptDto dto : deptList) {
            System.out.printf("| %-12s | %-30s |\n", dto.getDeptCode(), dto.getDeptName());
        }

        System.out.println("+--------------+--------------------------------+");

        System.out.print("부서 번호를 입력하세요. 예약을 그만두려면 -1을 입력하세요. : ");
        int dept = Integer.parseInt(sc.nextLine());

        return dept;
    }

    public static int doctorTimeList(List<DoctorDto> doctorList) {
        System.out.println();
        System.out.println("--------------- 의사 선택창 --------------------");

        System.out.println("+--------------+--------------------------------+");
        System.out.printf("| %-12s | %-30s |\n", "의사 번호", "의사 이름");
        System.out.println("+--------------+--------------------------------+");

        for (DoctorDto dto : doctorList) {
            System.out.printf("| %-12s | %-30s |\n", dto.getDoctorSeq(), dto.getDoctorName());
        }

        System.out.println("+--------------+--------------------------------+");

        System.out.print("의사 번호 입력하세요. 예약 그만두려면 -1을 입력하세요. : ");
        int doctor = Integer.parseInt(sc.nextLine());

        return doctor;
    }

    public static int[] reservationTimeArray(int[][] availableTimeArray) {
        int ROW = 3;
        int COL = 19;
        int[] timeBlock = new int[2];

        while (true) {
            System.out.println();
            System.out.println("--- 시간 선택 (■ : 선택 가능, □ : 선택 불가능) ---");
            System.out.print("    ");
            for (int col = 1; col < 19; col++) {
                int hours = (col + 17) / 2;
                int minutes = ((col + 17) % 2) * 30;
                System.out.printf("%02d:%02d ", hours, minutes);
            }
            System.out.println();
            System.out.print("\t");
            for (int col = 1; col < COL; col++) {
                System.out.printf("%-6d", col);
            }
            System.out.println();

            for (int row = 0; row < ROW; row++) {
                System.out.printf("%-3d ", row);

                for (int col = 1; col < COL; col++) {
                    if (availableTimeArray[row][col] >= 0) {
                        // 선택 가능
                        System.out.printf("%-6s", "■");
                    } else {
                        // 선택 불가능
                        System.out.printf("%-6s", "□");
                    }
                }
                System.out.println();
            }

            System.out.println("--------------------------------------------------");
            System.out.println("예약을 중단하시려면 -1을 입력해주세요. ");

            System.out.print("날짜를 선택해 주세요. (오늘 0, 내일 1, 모레 2) : ");
            timeBlock[0] = Integer.parseInt(sc.nextLine());
            if (timeBlock[0] < -1 || timeBlock[0] > 2) {
                System.out.println("날짜를 잘못 입력하셨습니다. ");
                continue;
            }
            
            System.out.print("시간을 선택해 주세요. : ");
            timeBlock[1] = Integer.parseInt(sc.nextLine());
            if (timeBlock[1] < -1 || timeBlock[1] > 18) {
                System.out.println("날짜를 잘못 입력하셨습니다. ");
                continue;
            }

            if (availableTimeArray[timeBlock[0]][timeBlock[1]] == -1) {
                System.out.println("해당 시간은 선택할 수 없습니다.");
                continue;
            }

            break;

        }
        return timeBlock;
    }

    public static int reservationSeq() {
        System.out.print("취소할 예약 번호 입력 : ");
        int num = Integer.parseInt(sc.nextLine());
        return num;
    }

    public static int reservationSeqUpdate() {
        System.out.print("변경할 예약 번호 입력 : ");
        int num = Integer.parseInt(sc.nextLine());
        return num;
    }
}
