Ext.define('SGCEscalada.view.ciudad.EliminarCiudad', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarCiudad',
    autoShow: true,
    title: 'Eliminar Ciudad',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxEliminarCiudad = Ext.widget('cbxCiudad');

        me.fsDatosAdicionarCiudad = Ext.create('Ext.form.FieldSet', {
            title: 'Datos ciudad:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.cbxEliminarCiudad]
        });

        me.btnAceptarAdicionarCiudad = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'eliminarCiudad'
        });

        me.btnCancelarAdicionarCiudad = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarCiudad'
        });

        me.frmAdicionarCiudad = Ext.create('Ext.form.Panel', {            //
            border: 0,
            items: [me.fsDatosAdicionarCiudad],
            buttons: [me.btnCancelarAdicionarCiudad, me.btnAceptarAdicionarCiudad]
        });


        me.items = [me.frmAdicionarCiudad];
        me.callParent(arguments);
    }
});