Ext.define('SGCEscalada.view.lugar.EliminarLugar', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarLugar',
    autoShow: true,
    title: 'Eliminar Lugar',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxEliminarLugares = Ext.widget('cbxLugares');

        me.fsDatosAdicionarLugar = Ext.create('Ext.form.FieldSet', {
            title: 'Lugar:',
            layout: 'fit',
            margin: 5,
            padding: 5,
            items: [me.cbxEliminarLugares]
        });

        me.btnCancelarAdicionarLugar = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarLugar'
        });

        me.btnAceptarAdicionarLugar = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'eliminarLugar'
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