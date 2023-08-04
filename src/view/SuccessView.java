package view;

import model.dto.DiagnosisDto;
import model.dto.ReservationDto;

import java.util.List;

public class SuccessView {

    /**
     * 성공 메시지 출력
     *
     * @param message 성공 메시지
     */
    public static void messagePrint(String message) {
        System.out.println(message);
    }

    /**
     * 예약 내역 출력
     *
     * @param reservationList 예약 리스트
     */
    public static void reservationList(List<ReservationDto> reservationList) {
        System.out.println("+----------------+--------------+-----------------------+");
        System.out.println("| 예약 번호      | 예약 일      | 예약 시간             |");
        System.out.println("+----------------+--------------+-----------------------+");
        for (ReservationDto reservation : reservationList) {
            System.out.printf("| %-14d | %-13s | %-20s |\n",
                    reservation.getReservationSeq(),
                    extractDate(reservation.getReservationDate()),
                    getFormattedTime(reservation.getReservationBlockSeq())
            );
        }
        System.out.println("+----------------+--------------+-----------------------+");
    }

    /**
     * 진료 내역 출력
     *
     * @param diagnosisList 진료 리스트
     */
    public static void diagnosisList(List<DiagnosisDto> diagnosisList) {
        System.out.println("+--------------+--------------------------------+");
        System.out.println("| 진료일       |        처방                    |");
        System.out.println("+--------------+--------------------------------+");
        for (DiagnosisDto diagnosis : diagnosisList) {
            System.out.printf("| %-12s | %-30s |\n", extractDate(diagnosis.getDiagnosisDate()), diagnosis.getDiagnosisPrescription());
        }
        System.out.println("+--------------+--------------------------------+");
    }

    /**
     * 시간 포맷 출력
     *
     * @param reservationBlockSeq 0 ~ 17 <- 9 ~ 5:30
     * @return HH:MM
     */
    private static String getFormattedTime(int reservationBlockSeq) {
        int hours = reservationBlockSeq / 2 + 9;
        int minutes = (reservationBlockSeq % 2) * 30;
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String extractDate(String originalDate) {
        return originalDate.substring(0, 10);
    }
}
