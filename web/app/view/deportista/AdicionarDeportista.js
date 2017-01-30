Ext.define('SGCEscalada.view.deportista.AdicionarDeportista', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarDeportista',
    autoShow: true,
    title: 'Adicionar Deportista',
    width: 623,
//    height: 555,
    resizable: false,
    modal: true,
    layout:'fit',

    initComponent: function () {
        var me = this;

        //fset credenciales: nombre/cedula/fecha
        me.ftNombreDeportista = Ext.create('Ext.form.field.Text',{
            name: 'nombreDeportista',
            labelAlign:'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5,
            fieldLabel: 'Nombre(s)'
        });
        me.ftPrimerApellidoDeportista = Ext.create('Ext.form.field.Text',{
            name: 'pApellido',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5,
            fieldLabel: 'Primer Apellido'
        });
        me.ftSegundoApellidoDeportista = Ext.create('Ext.form.field.Text',{
            name: 'sApellido',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5,
            fieldLabel: 'Segundo Apellido'
        });
        me.ftCedulaDeportista = Ext.create('Ext.form.field.Text', {
            name: 'cedula',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5,
            fieldLabel: 'C&eacute;dula'
        });
        me.fdFechaNacimientoDeportista = Ext.create('Ext.form.field.Date', {
            name: 'fechaNacimiento',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            editable:false,
            padding: 5,
            margin: 5,
            fieldLabel: 'Fecha Nacimiento',
            maxValue: new Date()
        });

        me.fsCredencialesDeportista = Ext.create('Ext.form.FieldSet', {
            title: 'Credenciales:',
            layout: 'column',
            padding: 5,
            margin: 5,
            items: [
                me.ftNombreDeportista,
                me.ftPrimerApellidoDeportista,
                me.ftSegundoApellidoDeportista,
                me.ftCedulaDeportista,
                me.fdFechaNacimientoDeportista
            ]
        });

        //fset direccion
        me.fsDireccionDeportista = Ext.widget('fsDireccion', {
            layout: 'column'
        });
        me.fsDireccionDeportista.cbxProvinciaDireccion.labelAlign = 'top';
        me.fsDireccionDeportista.cbxCantonDireccion.labelAlign = 'top';
        me.fsDireccionDeportista.cbxCiudadDireccion.labelAlign = 'top';
        me.fsDireccionDeportista.cbxParroquiaDireccion.labelAlign = 'top';

        //fset federacion: fecha ingreso/numero de registro
        me.cbxFederacionDeportista = Ext.widget('cbxFederaciones');
        me.fdFechaIngresoDeportista = Ext.create('Ext.form.field.Date', {
            name: 'fechaIngreso',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            editable: false,
            padding: 5,
            margin: 5,
            fieldLabel: 'Fecha Ingreso',
            maxValue: new Date()
        });
        me.ftNumeroRregistroDeportista = Ext.create('Ext.form.field.Number', {
            name: 'numeroRregistro',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            minValue: 0,
            hideTrigger: true,
            padding: 5,
            margin: 5,
            fieldLabel: 'No. Registro'
        });

        me.fsFederacionDeportista = Ext.create('Ext.form.FieldSet', {
            title: 'Datos de la federaci&oacute;n provincial:',
            layout: 'column',
            padding: 5,
            disabled:true,
            margin: 5,
            items: [
                me.cbxFederacionDeportista,
                me.fdFechaIngresoDeportista,
                me.ftNumeroRregistroDeportista
            ]
        });

        //fset federacion: tipo sangre/sexo
        me.cbxSexoDeportista = Ext.create('Ext.form.field.ComboBox',{
            name: 'sexo',
            fieldLabel: 'Sexo',
            labelAlign:'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            queryMode: 'local',
            editable: false,
            padding: 5,
            margin: 5,
            store: Ext.create('Ext.data.Store', {
                fields: ["idSexo", "sexo"],
                data: [
                    {idSexo: 1, sexo: "Masculino"},
                    {idSexo: 2, sexo: "Femenino"}]
            }),
            displayField: "sexo",
            valueField: "idSexo"
        });
        me.cbxTipoSangreDeportista = Ext.create('Ext.form.field.ComboBox',{
            name: 'tipoSangre',
            fieldLabel: 'Tipo de Sangre',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            queryMode: 'local',
            editable: false,
            padding: 5,
            margin: 5,
            store: Ext.create('Ext.data.Store', {
                fields: ["idTipoSangre", "tipoSangre"],
                data: [
                    {idTipoSangre: 1, tipoSangre: "AB+"},
                    {idTipoSangre: 2, tipoSangre: "AB-"},
                    {idTipoSangre: 3, tipoSangre: "A+"},
                    {idTipoSangre: 4, tipoSangre: "A-"},
                    {idTipoSangre: 5, tipoSangre: "B+"},
                    {idTipoSangre: 6, tipoSangre: "B-"},
                    {idTipoSangre: 7, tipoSangre: "O+"},
                    {idTipoSangre: 8, tipoSangre: "O-"}
                ]
            }),
            displayField: "tipoSangre",
            valueField: "idTipoSangre"
        });

        me.fsOtrosDatosDeportista = Ext.create('Ext.form.FieldSet', {
            title: 'Otros datos:',
            layout: 'column',
            padding: 5,
            margin: 5,
            items: [
                me.cbxSexoDeportista,
                me.cbxTipoSangreDeportista
            ]
        });

        me.btnAceptarAdicionarDeportista = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarDeportistaWnd'
        });

        me.btnCancelarAdicionarDeportista = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarDeportista'
        });

        me.frmAdicionarDeportista = Ext.create('Ext.form.Panel', {
            border:0,
            items: [
                me.fsCredencialesDeportista,
                me.fsDireccionDeportista,
                me.fsFederacionDeportista,
                me.fsOtrosDatosDeportista
            ],
            buttons: [me.btnCancelarAdicionarDeportista, me.btnAceptarAdicionarDeportista]
        });



        this.items = [me.frmAdicionarDeportista];
        this.callParent(arguments);
    }
});