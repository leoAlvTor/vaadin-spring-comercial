package microdev.vaadinspringcomercial.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import microdev.vaadinspringcomercial.model.ClientModel;

import java.util.Objects;


public class FacturacionView extends Div {

    private ClientModel selectedClient;

    public FacturacionView() {
        selectedClient = new ClientModel();
        addTopComponent();
    }

    private void addTopComponent() {
        Button addClientDataButton = new Button("Buscar Cliente");
        addClientDataButton.addClickListener(e -> addClientDataButtonEvent());

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


    private void addClientDataButtonEvent() {
        // TODO Refactor this method to make it shorter!
        Dialog clientSearchDialog = new Dialog();
        clientSearchDialog.setHeaderTitle("Buscar un cliente");

        ComboBox<ClientModel> cedulaClienteComboBox = new ComboBox<>("Cedula/RUC:");
        // TODO: Remove dummy data for real data
        cedulaClienteComboBox.setItems(ClientModel.getDummyData());
        cedulaClienteComboBox.setItemLabelGenerator(ClientModel::getId);
        cedulaClienteComboBox.setPlaceholder("Ingrese el ID del cliente");
        cedulaClienteComboBox.setAllowCustomValue(true);

        ComboBox<ClientModel> nombreClienteComboBox = new ComboBox<>("Nombre:");
        //Custom Filter for lowercase name searching
        ComboBox.ItemFilter<ClientModel> nombreItemFilter = (client, filterString)
                -> client.getNombre().toLowerCase().contains(filterString.toLowerCase());
        // TODO: Dummy data
        nombreClienteComboBox.setItems(nombreItemFilter, ClientModel.getDummyData());
        nombreClienteComboBox.setItemLabelGenerator(ClientModel::getNombre);
        nombreClienteComboBox.setPlaceholder("Ingrese el nombre del cliente");
        nombreClienteComboBox.setAllowCustomValue(true);

        TextField direccionTextField = new TextField("Direccion");
        TextField telefonoTextField = new TextField("Telefono");
        EmailField emailEmailField = new EmailField("Correo:");
        emailEmailField.setErrorMessage("Correo invalido");

        cedulaClienteComboBox.addValueChangeListener(e -> {
            this.selectedClient = e.getValue();
            this.showClientData(nombreClienteComboBox, emailEmailField, direccionTextField, telefonoTextField);
        });
        cedulaClienteComboBox.addCustomValueSetListener(e -> this.selectedClient.setId(e.getDetail()));
        nombreClienteComboBox.addValueChangeListener(e -> {
            this.selectedClient = e.getValue();
            this.showClientData(cedulaClienteComboBox, emailEmailField, direccionTextField, telefonoTextField);
        });
        // Listener for custom values
        nombreClienteComboBox.addCustomValueSetListener(e -> this.selectedClient.setNombre(e.getDetail()));

        Button enviarClienteButton = new Button("Seleccionar Cliente");
        enviarClienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        enviarClienteButton.addClickListener(e -> enviarClienteAction(direccionTextField.getValue(),
                telefonoTextField.getValue(), emailEmailField));

        Button enviarConsumidorFinalButton = new Button("Consumidor Final");
        enviarConsumidorFinalButton.addClickListener(e -> enviarConsumidorFinalAction());

        FormLayout datosClienteFormLayout = new FormLayout();



        datosClienteFormLayout.add(cedulaClienteComboBox, nombreClienteComboBox, direccionTextField, telefonoTextField,
                emailEmailField, enviarClienteButton, enviarConsumidorFinalButton);

        // Add form layout to dialog
        clientSearchDialog.add(datosClienteFormLayout);

        clientSearchDialog.open();
    }

    private void showClientData(ComboBox<ClientModel> comboBox, EmailField emailField, TextField... textFields){
        comboBox.setValue(this.selectedClient);
        emailField.setValue(this.selectedClient.getEmail());
        textFields[0].setValue(this.selectedClient.getDireccion());
        textFields[1].setValue(this.selectedClient.getTelefono());
    }

    private void enviarClienteAction(String direccion, String telefono, EmailField email) {
        if(Objects.nonNull(this.selectedClient.getId()) && Objects.nonNull(this.selectedClient.getNombre())){
            if(!direccion.isBlank() && !telefono.isBlank() && (!email.isInvalid() && !email.getValue().isBlank())){
                this.selectedClient.setDireccion(direccion);
                this.selectedClient.setTelefono(telefono);
                this.selectedClient.setEmail(email.getValue());
                System.out.println(selectedClient);
            }else{
                Notification.show("Revise que los campos direccion, telefono o email esten correctamente ingresados",
                        5000, Notification.Position.TOP_CENTER);
            }
        }else{
            Notification.show("Ingrese el RUC y el Nombre correctamente",
                    5000, Notification.Position.TOP_CENTER);
        }
    }

    private void enviarConsumidorFinalAction() {
        Notification.show("Se generara la factura como consumidor final!", 5000, Notification.Position.TOP_CENTER);
    }

}
