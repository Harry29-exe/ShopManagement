<script lang="ts">
    import {TableHeader} from "./TableHeader";

    export let header: TableHeader<any> = TableHeader.fromArray([]);
    export let values: any[] = [];

    function checkIfRowEmpty(rowValue: any) {
        return rowValue == undefined || rowValue == null? "-": rowValue;
    }
</script>

<div class="table-wrapper">
<table>
    <thead>
    <tr>
        {#each header.columns as column}
            <th>{column.name}</th>
        {/each}
    </tr>
    </thead>

    <tbody>
    {#each values as value, i}
        <tr on:click={() => header.onRowClick(value)}>
            {#each header.columns as column}
                <td>{checkIfRowEmpty(column.getValue(value))}</td>
            {/each}
        </tr>
    {/each}
    </tbody>

</table>
</div>

<style>
    table {
        @apply text-lg font-medium w-full ;
        border-collapse: collapse;
    }

    thead tr {
        @apply bg-primary-100;
    }

    thead th {
        @apply border-0 border-b-2 border-primary-700;
    }

    td, th {
        @apply border-2 border-bg-400 font-medium p-1;
    }

    th {
        @apply font-bold;
    }

    tbody tr {
        @apply hover:bg-black-alpha-100 hover:cursor-pointer
    }

    .table-wrapper {
        @apply shadow-dark-md rounded-2xl overflow-hidden;
    }


</style>