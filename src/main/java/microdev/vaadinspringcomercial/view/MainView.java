package microdev.vaadinspringcomercial.view;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import microdev.vaadinspringcomercial.repository.ProdTemRepository;

@Route("/main_view")
@PageTitle("Factura")
public class MainView extends Div implements BeforeEnterObserver {

    private final VaadinSession session;
    private ProdTemRepository prodTemRepository;

    public MainView(ProdTemRepository prodTemRepository){
        this.prodTemRepository = prodTemRepository;
        session = VaadinSession.getCurrent();
        addTab();
    }

    public void addTab() {
        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");


        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Facturacion",
                new LazyComponent(()->new FacturacionView(this.prodTemRepository)));

        add(tabSheet);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        try{
            session.getAttribute("user").toString();
        }catch (Exception e){
            //UI.getCurrent().access(() -> UI.getCurrent().navigate("login", QueryParameters.of("deny", "No puede acceder al sistema si " +
            //        "aún no inicia sesión!")));
            System.out.println("\u001B[31m"+ "-----------> UNCOMMENT!" + "\u001B[31m");
        }
    }

    private void redirectoToLogin(){

    }
}
