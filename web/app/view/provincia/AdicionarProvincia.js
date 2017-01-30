Ext.define('SGCEscalada.view.provincia.AdicionarProvincia', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarProvincia',
    autoShow: true,
    title: 'Adicionar Provincia',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.ftNombreProvincia = Ext.create('Ext.form.field.Text',{
            fieldLabel: 'Nombre',
            allowBlank: false,
            name:'nombreProvincia',
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5

        });

        me.fsDatosAdicionarProvincia = Ext.create('Ext.form.FieldSet',{
            title: 'Datos provincia:',
            padding: 5,
            margin: 5,
            items:[me.ftNombreProvincia]
        });

        me.btnAceptarAdicionarProvincia = Ext.create('Ext.button.Button',{
            formBind:true,
            text: 'Aceptar',
            action:'aceptarAdicionarProvincia'
        });

        me.btnCancelarAdicionarProvincia = Ext.create('Ext.button.Button',{
            text: 'Cancelar',
            action:'cancelarAdicionarProvincia'
        });

        me.frmAdicionarProvincia = Ext.create('Ext.form.Panel',{
            //padding:5,
            border:0,
            items:[me.fsDatosAdicionarProvincia],
            buttons:[me.btnCancelarAdicionarProvincia, me.btnAceptarAdicionarProvincia]
        });


        me.items = [me.frmAdicionarProvincia];
        me.callParent(arguments);
    }
});