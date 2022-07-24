import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var dataInicio = LocalDate.of(2022, 1, 15);
        var dataFim = LocalDate.of(2022, 3, 8);

        for (Integer ignored : mesesEntreDatas(dataInicio, dataFim)) {
            var dataInicialMes = dataInicio;
            var dataFinalMes = dataInicialMes
                    .plusMonths(1)
                    .withDayOfMonth(1)
                    .minusDays(1);
            if (dataFinalMes.isAfter(dataFim)) {
                dataFinalMes = dataFim;
            }
            System.out.println("periodo: " + dataInicialMes + " - " + dataFinalMes);
            dataInicio = dataFinalMes.plusDays(1);
        }

    }

    public static List<Integer> mesesEntreDatas(LocalDate dataInicio, LocalDate dataFim) {
        List<Integer> meses = new ArrayList<>();
        if (dataInicio.isBefore(dataFim) || dataInicio.equals(dataFim)) {
            var listaDatas = dataInicio.datesUntil(dataFim).collect(Collectors.toList());
            listaDatas.add(dataFim);
            listaDatas.forEach(data -> {
                var novoMesEncontrado = data.getMonthValue();
                if (meses.isEmpty() || meses.get(meses.size() - 1) != novoMesEncontrado) {
                    meses.add(novoMesEncontrado);
                }
            });
        }
        return meses;
    }
}
