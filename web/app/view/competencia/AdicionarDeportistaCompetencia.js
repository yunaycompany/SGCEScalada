Ext.define('SGCEscalada.view.competencia.AdicionarDeportistaCompetencia', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarDeportistaCompetencia',
    autoShow: true,
    title: 'Adicionar deportista a competencia',
    resizable: false,
    layout: 'fit',
    height: 550,
    width: 624,
    modal: true,

    initComponent: function () {
        var me = this;

        me.cbxCompetenciasAdicionarDeportistaCompetencia = Ext.widget('cbxCompetencias',{
            labelAlign: 'top',
            width: 368
        });

        me.fsNombreCompetenciaAdicionarDeportistaCompetencia = Ext.create('Ext.form.FieldSet', {
            title: 'Nombre competencia:',
            padding: 5,
            margin: 5,
            items: [me.cbxCompetenciasAdicionarDeportistaCompetencia]
        });

        me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia = Ext.widget('grdDeportistaCompetencia',{
            padding: '5 1',
            margin: '4'
        });

        me.btnAceptarAdicionarDeportistaCompetencia = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarDeportistaCompetencia'
        });

        me.btnCancelarAdicionarDeportistaCompetencia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarDeportistaCompetencia'
        });

        me.frmAdicionarDeportistaCompetencia = Ext.create('Ext.form.Panel',{
            border:false,
            items:[me.fsNombreCompetenciaAdicionarDeportistaCompetencia, me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia],
            buttons: [me.btnCancelarAdicionarDeportistaCompetencia, me.btnAceptarAdicionarDeportistaCompetencia]
        });

        me.items = [me.frmAdicionarDeportistaCompetencia];

        me.callParent(arguments)
    }
});