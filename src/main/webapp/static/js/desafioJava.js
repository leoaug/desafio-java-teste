function convertDateFormat(dateStr) {
    // Divide a string no formato dd/MM/yyyy
    const [day, month, year] = dateStr.split('/');

    // Reorganiza para o formato yyyy-MM-dd
    return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
}

function convertMoney(valor) {
	let valorFormatado = valor.toLocaleString('pt-BR', {
  style: 'currency',
  currency: 'BRL'
});

return valorFormatado;
}