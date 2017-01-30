Ext.define('SGCEscalada.view.fase.WndCargarFase', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarFederacion',
    autoShow: true,
    title: 'Adicionar federaci&oacute;n',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.ftNombreFederacion = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            name: 'nombreFederacion',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5
        });

        me.cbxProvinciaAdicionarFederacion = Ext.widget('cbxProvincias');

        me.fsDatosAdicionarFederacion = Ext.create('Ext.form.FieldSet', {
            title: 'Datos federaci&oacute;n:',
            layout: 'anchor',
            padding: 5,
            margin: 5,
            items: [me.cbxProvinciaAdicionarFederacion, me.ftNombreFederacion]
        });

        me.btnAceptarAdicionarFederacion = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarFederacion'
        });

        me.btnCancelarAdicionarFederacion = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarFederacion'
        });

        me.frmAdicionarFederacion = Ext.create('Ext.form.Panel', {
            //padding: 5,
            border: 0,
            items: [me.fsDatosAdicionarFederacion],
            buttons: [me.btnCancelarAdicionarFederacion, me.btnAceptarAdicionarFederacion]
        });


        me.items = [me.frmAdicionarFederacion];
        me.callParent(arguments)
    }
});
