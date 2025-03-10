package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.model.Alimento;
import br.com.fiap.calorias.repository.AlimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public AlimentoExibicaoDTO salvarAlimento(AlimentoCadastroDTO alimentoDTO){

        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoDTO, alimento);

        alimento.setTotalCalorias(
                calcularCalorias(
                        alimento.getQuantidadeProteina(),
                        alimento.getQuantidadeCarboidrato(),
                        alimento.getQuantidadeGorduras()
                )
        );

        Alimento alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibicaoDTO(alimentoSalvo);

    }


    public AlimentoExibicaoDTO buscarPorId(Long id){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }


    public List<AlimentoExibicaoDTO> listarTodos(){
        return alimentoRepository
                .findAll()
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }


    public void excluir(Long id){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }


    public AlimentoExibicaoDTO atualizar(AlimentoCadastroDTO alimentoDTO){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(alimentoDTO.alimentoId());

        if (alimentoOptional.isPresent()){
            Alimento alimento = new Alimento();
            BeanUtils.copyProperties(alimentoDTO, alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGorduras()
                    )
            );

            return new AlimentoExibicaoDTO(alimentoRepository.save(alimento));
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }


    public Double calcularCalorias(Double proteinas, Double carboidratos, Double gorduras){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gorduras *9);
        return calorias;
    }


    public AlimentoExibicaoDTO buscarAlimentoPorNome(String nome){
        Optional<Alimento> alimentoOptional = alimentoRepository.buscarAlimentoPorNome(nome);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }


    public List<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(Double minimo, Double maximo){
        return alimentoRepository
                .listarAlimentosPorFaixaDeCalorias(minimo, maximo)
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }
}
