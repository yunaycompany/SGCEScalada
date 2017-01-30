Ext.define('SGCEscalada.view.canton.AdicionarCanton', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarCanton',
    autoShow: true,
    title: 'Adicionar Cant&oacute;n',
    resizable: false,
    modal: true,
    layout: 'fit',
    width:309,

    initComponent: function () {
        var me = this;

        me.ftNombreCanton = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            name:'nombreCanton',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5
        });

        me.cbxProvinciaAdicionarCanton = Ext.widget('cbxProvincias');

        me.fsDatosAdicionarCanton = Ext.create('Ext.form.FieldSet', {
            title: 'Datos cant&oacute;n:',
            layout: 'anchor',
            padding: 5,
            margin: 5,
            items: [me.cbxProvinciaAdicionarCanton, me.ftNombreCanton]
        });

        me.btnAceptarAdicionarCanton = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarCanton'
        });

        me.btnCancelarAdicionarCanton = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarCanton'
        });

        me.frmAdicionarCanton= Ext.create('Ext.form.Panel', {
            //padding: 5,
            border: 0,
            items: [me.fsDatosAdicionarCanton],
            buttons: [me.btnCancelarAdicionarCanton, me.btnAceptarAdicionarCanton]
        });


        me.items = [me.frmAdicionarCanton];
        me.callParent(arguments);
    }
});