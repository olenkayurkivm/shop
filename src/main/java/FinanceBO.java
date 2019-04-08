import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.back;

/**
 * Created by OYurkiv on 10/1/2018.
 */
public class FinanceBO {

//    private FinancePage financePage;
//
//    List<String> absChanges = new ArrayList<>();
//    List<String> relChanges = new ArrayList<>();
//    List<String> indxOHLValues = new ArrayList<>();
//
//    public FinanceBO(String ip, String browser) {
//        financePage = new FinancePage(ip, browser);
//    }
//
//    public void  commitRate(String comment){
//        financePage.rate1();
//        financePage.rate2();
//        financePage.rate3();
//        financePage.leaveComment(comment);
//
//    }
//
//    public List<String> getWorldIndexesAbsoluteChanges(){
//        IntStream.range(0,5).forEach(i ->  absChanges.add(financePage.getIndexAbsoluteChangeOf(i)));
//        return absChanges;
//    }
//
//    public List<String> getWorldIndexesRelativeChanges(){
//        IntStream.range(0,5).forEach(i ->  relChanges.add(financePage.getIndexRelativeChangeOf(i)));
//        return relChanges;
//    }
//
//    public List<String> getIndexOpenHighLowValues(){
//        IntStream.range(0,5).forEach(i -> {
//            financePage.clickOnIndexOf(i);
//            indxOHLValues.add(financePage.getIndexOpenValue());
//            indxOHLValues.add(financePage.getIndexMaxValue());
//            indxOHLValues.add(financePage.getIndexMinValue());
//            back();
//        });
//        return indxOHLValues;
//    }

}
