select sample_id, dna_sequence, species,
    case when substr(dna_sequence, 1, 3) = 'ATG' then 1 else 0 end has_start,
    case when dna_sequence like '%TAA' or dna_sequence like '%TAG' or dna_sequence like '%TGA' then 1 else 0 end has_stop,
    case when dna_sequence like '%ATAT%' then 1 else 0 end has_atat,
    case when dna_sequence like '%GGG%' then 1 else 0 end has_ggg
from Samples
order by sample_id;