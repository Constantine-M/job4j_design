package ru.job4j.ood.srp.report.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.model.Employees;
import ru.job4j.ood.srp.report.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

/**
 * Данный класс описывает генерацию
 * отчета в формате XML.
 */
public class XMLReport implements Report {

    private static final Logger LOG = LoggerFactory.getLogger(XMLReport.class.getName());

    private final Store store;

    private Marshaller marshaller;

    public XMLReport(Store store) throws JAXBException {
        this.store = store;
        loadJAXBLib();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        StringBuilder xml = new StringBuilder();
        for (Employee servant : employees.getEmployees()) {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(servant, writer);
                xml.append(writer.getBuffer().toString());
            } catch (IOException | JAXBException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return xml.toString();
    }

    private void loadJAXBLib() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            LOG.error("Loading JAXB library failed! ", e);
        }
    }
}
