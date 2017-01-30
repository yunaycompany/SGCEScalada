Ext.define('SGCEscalada.view.lugar.AdicionarLugar', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarLugar',
    autoShow: true,
    title: 'Adicionar Lugar',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.fsDireccionLugar = Ext.widget('fsDireccion');

        me.ftNombreLugar = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            allowBlank: false,
            name: 'lugar',
            blankText: 'Este campo es requerido.',
            margin: 5,
            padding: 5
        });

        me.fsDatosAdicionarLugar = Ext.create('Ext.form.FieldSet', {
            title: 'Lugar:',
            margin: 5,
            padding: 5,
            items: [me.ftNombreLugar]
        });

        me.btnCancelarAdicionarLugar = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarLugar'
        });

        me.btnAceptarAdicionarLugar = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarLugar'
        });

        me.frmAdicionarLugar = Ext.create('Ext.form.Panel', {
            border: 0,
            //frame:true,
            items: [me.fsDireccionLugar, me.fsDatosAdicionarLugar ],
            buttons: [me.btnCancelarAdicionarLugar, me.btnAceptarAdicionarLugar]
        });

        me.items = [me.frmAdicionarLugar];

        me.callParent(arguments);
    }
});