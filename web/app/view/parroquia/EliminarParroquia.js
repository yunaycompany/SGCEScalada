Ext.define('SGCEscalada.view.parroquia.EliminarParroquia', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarParroquia',
    autoShow: true,
    title: 'Eliminar Parroquia',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxEliminarParroquia = Ext.widget('cbxParroquias');

        me.fsDatosAdicionarParroquia = Ext.create('Ext.form.FieldSet', {
            title: 'Datos parroquia:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.cbxEliminarParroquia]
        });

        me.btnAceptarAdicionarParroquia = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'eliminarParroquia'
        });

        me.btnCancelarAdicionarParroquia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarParroquia'
        });

        me.frmAdicionarParroquia = Ext.create('Ext.form.Panel', {            //
            border: 0,
            items: [me.fsDatosAdicionarParroquia],
            buttons: [me.btnCancelarAdicionarParroquia, me.btnAceptarAdicionarParroquia]
        });


        me.items = [me.frmAdicionarParroquia];
        me.callParent(arguments)
    }
});