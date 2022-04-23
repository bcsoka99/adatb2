package classes.views;

import classes.databaseclasses.entity.Jegy;
import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.repository.RepulojaratokRepository;
import classes.util.Converter;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("kimutatas")
@Route(value = "kimutatas")
public class YearlyStatement extends VerticalLayout {

    @Autowired
    RepulojaratokRepository repulojaratokRepository;

    DatePicker beforeDate;
    DatePicker afterDate;
    TextField summa;

    public YearlyStatement(){
        add(new Menu());
        add(beforeDate = new DatePicker("kezdo ido"));
        add(afterDate = new DatePicker("veg ido"));
        add(summa = new TextField("vegosszeg"));
        summa.setReadOnly(true);

        beforeDate.addValueChangeListener(change -> setSumma());
        afterDate.addValueChangeListener(change -> setSumma());
    }

    private void setSumma(){
        Double osszeg = 0.00;
        LocalDate before = beforeDate.getValue();
        LocalDate after = afterDate.getValue();
        List<Repulojaratok> repulojaratokList = (List<Repulojaratok>) repulojaratokRepository.findAll();
        List<Repulojaratok> filteredList = new ArrayList<>();
        for (Repulojaratok r : repulojaratokList){
            if (r.getIndulas().after(Converter.convertToDateViaInstant(before)) &&
                    r.getErkezes().after(Converter.convertToDateViaInstant(before)) &&
                    r.getIndulas().before(Converter.convertToDateViaInstant(after)) &&
                    r.getErkezes().before(Converter.convertToDateViaInstant(after))){
                filteredList.add(r);
            }
        }
        for (Repulojaratok r : filteredList){
            for (Jegy j : r.getJegy()){
                if (!j.getFoglalt()){
                    if (j.getUtasok().getGyerek()){
                        osszeg += j.getAr()*0.7;
                    }else {
                        osszeg += (double) j.getAr();
                    }
                }
            }
        }
        summa.setValue(String.valueOf(osszeg));
    }
}
