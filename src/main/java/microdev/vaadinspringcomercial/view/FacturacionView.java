package microdev.vaadinspringcomercial.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import microdev.vaadinspringcomercial.model.ClientModel;


public class FacturacionView extends Div {

    public FacturacionView(){
        addTopComponent();
    }

    private void addTopComponent(){
        Button addClientDataButton = new Button("Buscar Cliente");
        addClientDataButton.addClickListener(e->addClientDataButtonEvent());

        NumberField totalTxtArea = new NumberField();
        totalTxtArea.getStyle().set("color", "#DC143C");
        totalTxtArea.getStyle().set("font-size", "150%");
        totalTxtArea.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        totalTxtArea.setPrefixComponent(new Label("$"));
        // TODO: Bring the value from a controller package class.
        totalTxtArea.setValue(123.321);

        FormLayout formLayout = new FormLayout();
        formLayout.add(addClientDataButton, totalTxtArea);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        this.add(formLayout);
    }


    private void addClientDataButtonEvent(){
        Dialog clientSearchDialog = new Dialog();
        clientSearchDialog.setHeaderTitle("Buscar un cliente");

        Label cedulaLabel = new Label("Cedula:");
        ComboBox<ClientModel> cedulaClienteComboBox = new ComboBox<>();
        // TODO: Remove dummy data for real data
        cedulaClienteComboBox.setItems(ClientModel.getDummyData());
        cedulaClienteComboBox.setItemLabelGenerator(ClientModel::getId);
        cedulaClienteComboBox.setPlaceholder("Ingrese la cedula del cliente");
        // TODO: Check if its necessary :v
        cedulaClienteComboBox.setAllowCustomValue(true);
        // TODO: Add custom function to show client selected by its ID
        cedulaClienteComboBox.addValueChangeListener(System.out::println);

        FormLayout datosClienteFormLayout = new FormLayout();
        datosClienteFormLayout.add(cedulaLabel, cedulaClienteComboBox);

        // Add form layout to dialog
        clientSearchDialog.add(datosClienteFormLayout);

        clientSearchDialog.open();
    }

}
