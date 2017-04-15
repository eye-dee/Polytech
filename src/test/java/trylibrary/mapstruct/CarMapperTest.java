package trylibrary.mapstruct;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Polytech
 * Created by igor on 12.04.17.
 */
public class CarMapperTest {
    @Test
    public void carToCarDto() throws Exception {
        final Car car = new Car( "Morris", 5, CarType.SMALL);

        final CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        assertThat(carDto).isNotNull();
        assertThat(carDto.getMake()).isEqualTo( "Morris" );
        assertThat(carDto.getSeatCount()).isEqualTo( 5 );
        assertThat(carDto.getType()).isEqualTo( "SEDAN" );
    }

}