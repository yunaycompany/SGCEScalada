Ext.define('SGCEscalada.view.competencia.AdicionarCompetencia', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarCompetencia',
    autoShow: true,
    title: 'Adicionar competencia',
    resizable: false,
    layout: 'form',
    height: 'auto',
    width:424,
    modal: true,

    initComponent: function () {
        var me = this;

        me.fsDireccionCompetencia = Ext.widget('fsDireccion',{
            layout: 'column'
        });
        me.cbxLugarDireccion = Ext.widget('cbxLugares',{
            disabled:true
        });
        me.fsDireccionCompetencia.add(me.cbxLugarDireccion);
        me.fsDireccionCompetencia.cbxProvinciaDireccion.labelAlign = 'top';
        me.fsDireccionCompetencia.cbxCantonDireccion.labelAlign = 'top';
        me.fsDireccionCompetencia.cbxCiudadDireccion.labelAlign = 'top';
        me.fsDireccionCompetencia.cbxParroquiaDireccion.labelAlign = 'top';

        me.ftNombreCompetencia = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            labelAlign: 'top',
            name: 'nombreCompetencia',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            width:368,
            padding: 5,
            margin: 5
        });

        me.fsNombreCompetencia = Ext.create('Ext.form.FieldSet',{
            title: 'Nombre competencia:',
            padding: 5,
            margin: 5,
            items:[me.ftNombreCompetencia]
        });

        me.btnAceptarAdicionarCompetencia = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarCompetencia'
        });

        me.btnCancelarAdicionarCompetencia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarCompetencia'
        });

        me.frmAdicionarCompetencia = Ext.create('Ext.form.Panel',{
            border:0,
            items:[me.fsDireccionCompetencia, me.fsNombreCompetencia],
            buttons:[me.btnCancelarAdicionarCompetencia, me.btnAceptarAdicionarCompetencia]
        });

        me.items = [me.frmAdicionarCompetencia];
        me.callParent(arguments);
    }
});