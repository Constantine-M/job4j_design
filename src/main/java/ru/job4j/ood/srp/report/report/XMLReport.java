package ru.job4j.ood.srp.report.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.model.Employees;
import ru.job4j.ood.srp.report.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;

    private final DateTimeParser<Calendar> dateTimeParser;

    private static final Logger LOG = LoggerFactory.getLogger(XMLReport.class.getName());

    private JAXBContext context;

    private Marshaller marshaller;

    public XMLReport(Store store, DateTimeParser<Calendar> dateTimeParser) throws JAXBException {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        String xml = "";
        for (Employee servant : employees.getEmployees()) {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(servant, writer);
                xml = writer.getBuffer().toString();
            } catch (JAXBException e) {
                LOG.error("Something went wrong with JAXB - ", e);
            } catch (IOException ex) {
                LOG.error("Something went wrong with IO -", ex);
            }
        }
        return xml;
    }
}
