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

    public XMLReport(Store store) {
        this.store = store;
        loadJAXBLib();
    }

    /**
     * Данный метод сериализует сразу
     * весь список (коллекцию) объектов. Поэтому
     * мы и создавали класс {@link Employees}.
     * В нем добавили аннотацию "XmlRootElement".
     * Он выступает в качестве класса-обертки,
     * потому что коллекции не имеют аннотаций.
     * @param filter условия/е выборки сотрудников.
     * @return сериализованный список.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new IllegalArgumentException(e);
        }
        return xml;
    }

    private void loadJAXBLib() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            LOG.error("Loading JAXB library failed! ", e);
            throw new IllegalArgumentException();
        }
    }
}
