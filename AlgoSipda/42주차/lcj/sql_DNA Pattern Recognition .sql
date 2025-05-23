# Write your MySQL query statement below
select 
    sample_id, 
    dna_sequence, 
    species,
    left(dna_sequence, 3) in ('ATG') as has_start,
    right(dna_sequence, 3) in ('TAA', 'TAG', 'TGA') as has_stop,
    dna_sequence like '%ATAT%' as has_atat,
    dna_sequence regexp 'G{3,}' as has_ggg
from samples